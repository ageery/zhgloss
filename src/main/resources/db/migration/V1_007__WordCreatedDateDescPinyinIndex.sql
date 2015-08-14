drop index ix_word_created_date_pinyin;
create index ix_word_created_date_desc_pinyin on word(created_date desc, jsonb_array_attr_values_to_string(word_info, 'pinyin', ' ') asc);