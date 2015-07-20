create table cedict_load (
	cedict_load_id serial primary key,
	load_start timestamp with time zone not null,
	load_finish timestamp with time zone not null,
	load_count int not null
);

comment on table cedict_load is 'When and how many rows were loaded from the CEDICT data file.';

create index ix_cedict_load_load_start_load_finish on cedict_load(load_start, load_finish);