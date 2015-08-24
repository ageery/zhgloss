alter table transcription_system add ts_short_name text null;

update transcription_system set ts_short_name = ts_name;
update transcription_system set ts_short_name = 'Pinyin' where ts_code = 'H';

alter table transcription_system alter column ts_short_name set not null;