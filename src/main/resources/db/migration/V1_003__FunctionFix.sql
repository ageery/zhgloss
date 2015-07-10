create or replace function find_longest_word_prefix_nullable(t text, cc_type text) returns text as $$
declare r text;
begin
	case
		when cc_type = 'simp' then
			with z as (select jsonb_array_attr_values_to_string(word_info, 'simp', '') x 
				from word where jsonb_array_attr_values_to_string(word_info, 'simp', '') = any(array(select make_prefixes(t))))
			select x into r from z order by length(x) desc limit 1;
		when cc_type = 'trad' then
			with z as (select jsonb_array_attr_values_to_string(word_info, 'trad', '') x 
				from word where jsonb_array_attr_values_to_string(word_info, 'trad', '') = any(array(select make_prefixes(t))))
			select x into r from z order by length(x) desc limit 1;
	end case;
	return r;
end
$$ language 'plpgsql' stable;
comment on function find_longest_word_prefix_nullable(t text, cc_type text) is 'Finds the longest prefix/word from the text, with the given character type. If there is no prefix, returns null.';
