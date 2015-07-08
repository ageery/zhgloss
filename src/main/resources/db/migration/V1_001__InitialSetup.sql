create table transcription_system (
	ts_code text primary key,
	ts_name text
);
comment on table transcription_system is 'Phonetic systems for transcribing the Mandarin pronounciation of Chinese characters';

create table transcription_point (
	ts_code text references transcription_system(ts_code),
	pinyin_syllable text,
	tone int,
	untoned_representation text,
	toned_representation text,
	primary key (ts_code, pinyin_syllable, tone)
);
comment on table transcription_point is 'Toned transcription syllable info for a transcription system.';

create table cedict_word (
	id uuid primary key, 
	trad_chars text not null, 
	simp_chars text not null, 
	pinyin text not null
);
comment on table cedict_word is 'Character-related word data from the CEDICT file.';

create table cedict_word_def (
	id uuid primary key, 
	word_id uuid not null references cedict_word(id), 
	order_num int not null, 
	def text not null,
	unique(word_id, order_num)
);
comment on table cedict_word_def is 'Definition-related word data from the CEDICT file.';

create table word (
	id uuid primary key,
	word_info jsonb,
	created_date timestamptz default now(),
	updated_date timestamptz default now()
);
comment on table word is 'Character-related word data.';

create table word_def (
	id uuid primary key, 
	word_id uuid not null references word(id), 
	order_num int not null, 
	def text not null,
	def_tsv tsvector,
	created_date timestamptz default now(),
	updated_date timestamptz default now(),
	unique(word_id, order_num)
);
comment on table word_def is 'Definition-related word data.';

/*
 * Triggers.
 */
create trigger tr_word_def_def_tsv before insert or update on word_def for each row execute procedure tsvector_update_trigger('def_tsv', 'pg_catalog.english', 'def');

/*
 * Initial data.
 */
insert into transcription_system values 
	('Z','ZhuYin'),
	('W','Wade-Giles'),
	('M','MPS2'),
	('Y','Yale'),
	('T','Tongyong Pinyin'),
	('H','Hanyu Pinyin'),
	('G','Gwoyeu Romatzyh');

create or replace function make_word_info(w cedict_word) returns jsonb as $$
declare i int;
declare pinyin text;
declare m text[];
declare lowercase_syllable text;
declare char_arr text[];
declare chars_arr json[];
declare j text;
begin
    i := 0;
    foreach pinyin in array regexp_split_to_array(w.pinyin, E'\\s+') loop
	i := i + 1;
	select regexp_matches(pinyin, E'^(\\D+)(\\d)?$') into m;
	lowercase_syllable := lower(m[1]);

	j := '"pinyin" : "' || lowercase_syllable || '", ' || 
		'"trad" : "' || substring(w.trad_chars from i for 1) || '", ' || 
		'"simp" : "' || substring(w.simp_chars from i for 1) || '", ' ||
		'"index" : ' || i;
        if m[2] != '' then
	    j := j || ', "tone" : ' || m[2];
	end if;
        if lowercase_syllable != m[1] then
            j := j || ', "capitalized" : true';
        end if;
        chars_arr := chars_arr || ('{' || j || '}')::json;
    end loop;
    return array_to_json(chars_arr)::jsonb;
end
$$ language plpgsql immutable;
comment on function make_word_info(cedict_word) is 'Converts the data in a cedict_word row to jsonb data that can be stored in the word table.';

/*
 * Functions
 */
create or replace function jsonb_array_attr_values_to_string(jsonb_arr jsonb, attr_name text, separator text) returns text as $$
	select array_to_string(array(select jsonb_array_elements(jsonb_arr)->>attr_name), separator)
$$ language 'sql' immutable;
comment on function jsonb_array_attr_values_to_string(jsonb_arr jsonb, attr_name text, separator text) is 'Concatenates the values of the given attribute from each element in the jsonb array, separated by the separator.';

create or replace function jsonb_array_attr_values_to_arr(jsonb_arr jsonb, attr_name text) returns text[] as $$
	select array(select jsonb_array_elements(jsonb_arr)->>attr_name)
$$ language 'sql' immutable;

create or replace function get_cedict_word_parts(wi jsonb) returns table(trad_chars text, simp_chars text, pinyin text) as $$
declare j jsonb;
declare p text;
declare tone text;
begin
	simp_chars := '';
	trad_chars := '';
	pinyin := '';
	for j in select * from jsonb_array_elements(wi) loop
		simp_chars := simp_chars || (j->>'simp');
		trad_chars := trad_chars || (j->>'trad');
		tone := j ->>'tone';
		if tone is null then
			tone := '';
		end if;
		p := (j->>'pinyin') || tone;
		if (j ? 'capitalized') and (j ->> 'capitalized')::boolean then
			p := initcap(p);
		end if;
		pinyin := pinyin || p || ' ';
	end loop;
	pinyin := trim(pinyin);
	return next;
	return;
end
$$ language plpgsql immutable;
comment on function get_cedict_word_parts(jsonb) is 'Converts the pinyin-related data in a word.word_info representation to the same format as is in the cedict_word.pinyin column.';

/*
 * Indexes.
 */
create index ix_word_word_info on word using gin((word_info)); 
create index ix_word_word_info_arr on word using gin((word_info->0),(word_info->1),(word_info->2));
create index ix_word_simp_char_simp on word(jsonb_array_attr_values_to_string(word_info, 'simp', ''));
create index ix_word_simp_char_trad on word(jsonb_array_attr_values_to_string(word_info, 'trad', ''));
create index ix_word_def_word_id_order_num on word_def(word_id, order_num);
create index ix_word_def_def_tsv on word_def using gin(def_tsv);

-- pseudo primary key on the word table
create unique index ixu_word on word(
	jsonb_array_attr_values_to_arr(word_info, 'simp'),
	jsonb_array_attr_values_to_arr(word_info, 'trad'),
	jsonb_array_attr_values_to_arr(word_info, 'pinyin'), 
	jsonb_array_attr_values_to_arr(word_info, 'tone'),
	jsonb_array_attr_values_to_arr(word_info, 'capitalized'));
-- unique index on cedict_word
create unique index ixu_cedict_word on cedict_word(trad_chars, simp_chars, pinyin);

/*
 * Function to create prefixes from a word.
 */
create or replace function make_prefixes(txt text) returns setof text as $$
	with recursive x(t) as (
		select substring(txt for s - 1) from length(txt) s where s > 0
		union all
		select substring(t for s - 1) from x cross join lateral length(t) s where s > 1)
	select t from x
$$ language 'sql' immutable;
comment on function make_prefixes(txt text) is 'Creates all the prefixes of the given text.';

/*
 * Function to find the longest prefix for a  word. If no prefix can be found, 
  * return either the next character or the longest non-matching string, depending on the value of max_unmatched.
 */
create or replace function find_longest_word_prefix_nullable(t text, cc_type text) returns text as $$
declare r text;
begin
	case
		when cc_type = 'simp' then
			with z as (select jsonb_array_attr_values_to_string(word_info, 'simp', '') x 
				from word where jsonb_array_attr_values_to_string(word_info, 'simp', '') = any(array(select make_prefixes(t))))
			select x into r from z order by length(x) desc limit 1;
		when cc_type = 'trad' then
			with z as (select convert_word_info_part_to_text(word_info, 'trad', '') x 
				from word where jsonb_array_attr_values_to_string(word_info, 'trad', '') = any(array(select make_prefixes(t))))
			select x into r from z order by length(x) desc limit 1;
	end case;
	return r;
end
$$ language 'plpgsql' stable;
comment on function find_longest_word_prefix_nullable(t text, cc_type text) is 'Finds the longest prefix/word from the text, with the given character type. If there is no prefix, returns null.';

/*
 * Function to find the longest prefix for a  word. If no prefix can be found, 
 * return either the next character or the longest non-matching string, depending on the value of max_unmatched.
 */
create or replace function find_longest_word_prefix(t text, cc_type text, max_unmatched boolean) returns text as $$
declare x text;
declare longest_prefix text;
begin
	longest_prefix := find_longest_word_prefix_nullable(t, cc_type);
	if longest_prefix is null then
		if max_unmatched then
			longest_prefix := '';
			loop
				exit when x is not null or length(t) = 0;
				longest_prefix := longest_prefix || substring(t from 1 for 1);
				t := substring(t from 2 for length(t) - 1);
				x := find_longest_word_prefix_nullable(t, cc_type);
			end loop;
		else
			longest_prefix := substring(t from 1 for 1);
		end if;
	end if;
	return longest_prefix;
end
$$ language 'plpgsql' stable;
comment on function find_longest_word_prefix(t text, cc_type text, max_unmatched boolean)  is 'Finds the longest prefix/word from the text, with the given character type. If there is no prefix, either simply returns the next character or returns all of the next non-word characters, depending on the value of the max_unmatched param.';

/*
 * Function to segment the text into the longest words possible.
 */
create or replace function find_segments(t text, cc_type text, max_seg_len int, max_unmatched boolean) returns table(chars text, order_num int) as $$
	with recursive x (remaining_text, next_word, order_num) as (
		select substring(t from (1 + length(p))), p, 1 from find_longest_word_prefix(substring(t for max_seg_len), cc_type, max_unmatched) p
		union all
		select substring(remaining_text from (1 + length(p))), p, order_num + 1
		from x cross join lateral find_longest_word_prefix(substring(remaining_text for max_seg_len), cc_type, max_unmatched) p
		where length(remaining_text) > 0)
	select next_word, order_num from x
$$ language 'sql' stable;
comment on function find_segments(t text, cc_type text, max_seg_len int, max_unmatched boolean)  is 'Segments the text, with the given character type, into words. The maximum word length and whether to combine unmatched characters can be specified.';

/*
 * Function to turn the transcription info into json.
 */
create or replace function make_transcription_json(word_info jsonb, code text) returns json as $$
declare j jsonb;
declare a json[];
declare s text;
declare t text;
declare syllable text;
declare tr text;
declare ur text;
declare f boolean;
begin
	for j in select jsonb_array_elements(word_info) order by word_info->>('index') loop
		s := j->>('pinyin');
		t := j->>('tone');
		f := (j ? 'capitalized') and (j->>('capitalized'))::boolean;
		if t is null then
			if f then
				s := initcap(s);
			end if;
			a := array_append(a, json_object(array['toned-syllable', s]));
		else
			select toned_representation, untoned_representation into tr, ur from transcription_point where pinyin_syllable = s and tone = t::int and ts_code = code;
			if f then
				ur := initcap(ur);
				tr := initcap(tr);
			end if;
			a := array_append(a, json_object(array['untoned-syllable', ur, 'tone', t, 'toned-syllable', tr]));
		end if;
	end loop;
	return array_to_json(a);
end
$$ language plpgsql stable;
comment on function make_transcription_json(word_info jsonb, code text) is 'Creates a json reresentation of the transcription of the word in the transcription system specified by the code param.';

/*
 * Function to turn a word into json.
 */
create or replace function make_word_json(word_info jsonb, defs text[], ts_code text) returns json as $$
	select ('{' ||
		'"simplified-characters" : "' || jsonb_array_attr_values_to_string(word_info, 'simp', '') || '",' ||
		'"traditional-characters" : "' || jsonb_array_attr_values_to_string(word_info, 'trad', '') || '",' ||
		'"transcription" : ' || make_transcription_json(word_info, ts_code) || ',' ||
		'"definitions" : ' || array_to_json(defs) || '}')::json
$$ language 'sql' stable;
comment on function make_word_json(word_info jsonb, defs text[], ts_code text) is 'Creates a JSON reresentation of the word, where the transcription is in the given transcription system.';

/*
 * Function to update the words table with data not in the words table but that is in the cedict_word table.
 */
create or replace function update_words_from_cedict_words() returns int as $$
	with t as (select make_word_info(cedict_word) word_info from cedict_word except select word_info from word),
             x as (select wp.* from t cross join lateral get_cedict_word_parts(word_info) wp),
             u as (insert into word (id, word_info)
		select cedict_word.id, make_word_info(cedict_word) 
			from x inner join cedict_word on 
				cedict_word.trad_chars = x.trad_chars 
					and cedict_word.simp_chars = x.simp_chars 
					and cedict_word.pinyin = x.pinyin returning word.id),
	     v as (insert into word_def select * from cedict_word_def where word_id in (select id from u))
	     select count(*)::int from u;
$$ language 'sql' volatile;
comment on function update_words_from_cedict_words() is 'Adds word rows from cedict_word rows.';

/*
 * Function to segment the simplified text and return information about each of the words in it.
 */
create or replace function find_segments_details(txt text, cc_type text, ts_code text, max_seg_len int, max_unmatched boolean) returns table(chars text, order_num int, word_details json) as $$
	with x as (select t.chars, t.order_num char_order, word.id word_id, word_def.def, word_def.order_num def_order from find_segments(txt, cc_type, max_seg_len, max_unmatched) t
		left outer join word on t.chars = jsonb_array_attr_values_to_string(word.word_info, cc_type, '')
		left outer join word_def on word.id = word_def.word_id),
	y as (select chars, char_order, word_id, array_agg(def order by def_order) def_arr from x group by chars, char_order, word_id),
	z as (select chars, char_order, word_id, make_word_json(word.word_info, y.def_arr, ts_code) word_json from y left outer join word on y.word_id = word.id),
	w as (select chars, char_order, array_agg(word_json) word_info_arr from z group by chars, char_order)
	select chars, char_order, case when word_info_arr[1] is null then null else array_to_json(word_info_arr) end from w order by char_order
$$ language 'sql' stable;
comment on function find_segments_details(txt text, cc_type text, ts_code text, max_seg_len int, max_unmatched boolean) is 'Segments the text, with the given character type, into words and returns a JSON-array representation of each word.';

-- fixme: can we combine both of these functions into one? or do we just build this in the code?

create or replace function find_words(json_arr_search text, ts_code text) returns table(trad text, simp text, transcription text, transcription_json json, defs text[]) as $$
	with x as (select word.id word_id, word_info, array_agg(def order by order_num) def_arr from word
			inner join word_def on word.id = word_def.word_id
			where word_info @> json_arr_search::jsonb
			group by word.id)
	select jsonb_array_attr_values_to_string(word_info, 'trad', '') trad, jsonb_array_attr_values_to_string(word_info, 'simp', '') simp, jsonb_array_attr_values_to_string(z::jsonb, 'toned-syllable', ' ') transcription, z transcription_json, def_arr defs from x 
		cross join lateral make_transcription_json(word_info, ts_code) z
$$ language 'sql' stable;
comment on function find_words(json_arr_search text, ts_code text) is 'Returns words that match the word search criteria.';

create or replace function find_words_by_def(tsquery_search text, ts_code text) returns table(trad text, simp text, transcription text, transcription_json json, defs text[]) as $$
	with x as (select word.id word_id, word_info, array_agg(def order by order_num) def_arr from word
			inner join word_def on word.id = word_def.word_id
			where def_tsv @@ to_tsquery(tsquery_search)
			group by word.id)
	select jsonb_array_attr_values_to_string(word_info, 'trad', '') trad, jsonb_array_attr_values_to_string(word_info, 'simp', '') simp, jsonb_array_attr_values_to_string(z::jsonb, 'toned-syllable', ' ') transcription, z transcription_json, def_arr defs from x 
		cross join lateral make_transcription_json(word_info, ts_code) z
$$ language 'sql' stable;
comment on function find_words_by_def(tsquery_search text, ts_code text) is 'Returns words that match the definition search criteria.';

create view transcription_point_representations as (
	with t as (select pinyin_syllable, tone,  to_json(ts_code) || ' : {"toned-representation" : "' || toned_representation || '", "untoned-representation" : "' || untoned_representation || '"}' j from transcription_point),
	u as (select pinyin_syllable, tone, jsonb('{' || string_agg(j::text, ',') || '}') system_info from t group by pinyin_syllable, tone)
	select * from u);
comment on view transcription_point_representations is 'Transcription points with representation info for each system in JSON.';
