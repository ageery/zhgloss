CREATE OR REPLACE FUNCTION make_word_info(w cedict_word)
  RETURNS jsonb AS
$BODY$
declare i int;
declare pinyin text;
declare m text[];
declare lowercase_syllable text;
declare char_arr text[];
declare chars_arr json[];
declare j text;
declare suffix boolean;
declare make_lowercase boolean;
begin
    i := 0;
    foreach pinyin in array regexp_split_to_array(w.pinyin, E'\\s+') loop
    i := i + 1;
    select regexp_matches(pinyin, E'^(\\D+)(\\d)?$') into m;
    lowercase_syllable := lower(m[1]);
    make_lowercase := lowercase_syllable != m[1];
    if lowercase_syllable = 'r' then
        lowercase_syllable = 'er';
        suffix := true;
    else
        suffix := false;
    end if;

    j := '"pinyin" : "' || lowercase_syllable || '", ' || 
        '"trad" : "' || substring(w.trad_chars from i for 1) || '", ' || 
        '"simp" : "' || substring(w.simp_chars from i for 1) || '", ' ||
        '"index" : ' || i;
        if m[2] != '' then
        j := j || ', "tone" : ' || m[2];
    end if;
        if make_lowercase then
        j := j || ', "capitalized" : true';
        end if;
        if suffix then
        j := j || ', "suffix" : true';
        end if;
        chars_arr := chars_arr || ('{' || j || '}')::json;
    end loop;
    return array_to_json(chars_arr)::jsonb;
end
$BODY$ LANGUAGE plpgsql IMMUTABLE;
  
CREATE OR REPLACE FUNCTION make_transcription_json(
    word_info jsonb,
    code text)
  RETURNS json AS
$BODY$
declare j jsonb;
declare a json[];
declare s text;
declare t text;
declare syllable text;
declare tr text;
declare ur text;
declare f boolean;
declare x json;
declare suffix boolean;
begin
    for j in select jsonb_array_elements(word_info) order by word_info->>('index') loop
        s := j->>('pinyin');
        t := j->>('tone');
        f := (j ? 'capitalized') and (j->>('capitalized'))::boolean;
        if t is null then
            if f then
                s := upper(substring(s from 1 for 1)) || substring(s from 2);
            end if;
            a := array_append(a, json_object(array['toned-syllable', s]));
        else
            select toned_representation, untoned_representation into tr, ur from transcription_point where pinyin_syllable = s and tone = t::int and ts_code = code;
            if f then
                ur := initcap(ur);
                tr := initcap(tr);
            end if;
            suffix := (j ->>('suffix'))::boolean;
            if suffix is not null and suffix then
                select toned_representation into tr from transcription_point_extra 
                    where transcription_point_extra_type_id = 'suffix' 
                        and pinyin_syllable = s 
                        and tone = t::int 
                        and ts_code = code;
                if tr is not null then
                    a := array_append(a, json_object(array['untoned-syllable', ur, 'tone', t, 'toned-syllable', tr, 'suffix', 'true', 'suffix-toned-syllable', tr]));
        else
            a := array_append(a, json_object(array['untoned-syllable', ur, 'tone', t, 'toned-syllable', tr, 'suffix', 'true']));
                end if;
            else 
        a := array_append(a, json_object(array['untoned-syllable', ur, 'tone', t, 'toned-syllable', tr]));
            end if;
        end if;
    end loop;
    return array_to_json(a);
end
$BODY$ LANGUAGE plpgsql STABLE;
  
create table transcription_point_extra_type (
    id text not null primary key,
    type_desc text not null
);
COMMENT ON TABLE transcription_point_extra_type IS 'Type of extra transcription info.';
insert into transcription_point_extra_type values ('suffix', 'Suffix transcription representation');

create table transcription_point_extra (
  ts_code text NOT NULL references transcription_system(ts_code),
  pinyin_syllable text NOT NULL,
  tone integer NOT NULL,
  transcription_point_extra_type_id text not null references transcription_point_extra_type(id),
  untoned_representation text,
  toned_representation text,
  primary key (ts_code, pinyin_syllable, tone, transcription_point_extra_type_id)
);
COMMENT ON TABLE transcription_point_extra IS 'Extra transcription info.';
insert into transcription_point_extra values ('H', 'er', 5, 'suffix', 'r', 'r');

CREATE OR REPLACE FUNCTION get_cedict_word_parts(IN wi jsonb)
  RETURNS TABLE(trad_chars text, simp_chars text, pinyin text) AS
$BODY$
declare j jsonb;
declare p text;
declare tone text;
declare suffix boolean;
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
        suffix := (j ->>'suffix')::boolean;
        if suffix is not null and suffix then
            p := 'r' || tone;
        else
            p := (j->>'pinyin') || tone;
        end if;
        if (j ? 'capitalized') and (j ->> 'capitalized')::boolean then
            p := upper(substring(p from 1 for 1)) || substring(p from 2);
        end if;
        pinyin := pinyin || p || ' ';
    end loop;
    pinyin := trim(pinyin);
    return next;
    return;
end
$BODY$ LANGUAGE plpgsql IMMUTABLE;