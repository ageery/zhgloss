drop index ix_word_created_date;
create index ix_word_created_date_pinyin on word(created_date, jsonb_array_attr_values_to_string(word_info, 'pinyin', ' '));