CREATE OR REPLACE FUNCTION find_attr_index(
    jsonb_arr jsonb,
    attr_name text,
    attr_value text,
    occurrence_num integer)
  RETURNS integer AS
$BODY$
with x as (select (j->>'index')::int i from jsonb_array_elements(jsonb_arr) j where (j->>attr_name) = attr_value order by j->>'index' offset (occurrence_num - 1) limit 1)
select * from x
union 
select -1
order by i desc limit 1
$BODY$ LANGUAGE sql IMMUTABLE;
COMMENT ON FUNCTION find_attr_index(jsonb_arr jsonb, attr_name text, attr_value text, occurrence_num integer) 
IS 'Returns the index of the nth occurrence of a value for a given attribute name';

CREATE OR REPLACE FUNCTION word_length(jsonb_arr jsonb)
  RETURNS integer AS
$BODY$
select jsonb_array_length(jsonb_arr)
$BODY$ LANGUAGE sql IMMUTABLE;
COMMENT ON function word_length(jsonb_arr jsonb) IS 'Returns the length of a word stored as JSONB';