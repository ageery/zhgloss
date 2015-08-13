/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables;


import com.zhgloss.domain.WordInfo;
import com.zhgloss.jooq.LocalDateTimeConverter;
import com.zhgloss.jooq.WordInfoConverter;
import com.zhgloss.repo.jooq.Keys;
import com.zhgloss.repo.jooq.Public;
import com.zhgloss.repo.jooq.tables.records.WordRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * Character-related word data.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Word extends TableImpl<WordRecord> {

	private static final long serialVersionUID = -1882283296;

	/**
	 * The reference instance of <code>public.word</code>
	 */
	public static final Word WORD = new Word();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<WordRecord> getRecordType() {
		return WordRecord.class;
	}

	/**
	 * The column <code>public.word.id</code>.
	 */
	public final TableField<WordRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>public.word.word_info</code>.
	 */
	public final TableField<WordRecord, WordInfo> WORD_INFO = createField("word_info", org.jooq.impl.DefaultDataType.getDefaultDataType("jsonb"), this, "", new WordInfoConverter());

	/**
	 * The column <code>public.word.created_date</code>.
	 */
	public final TableField<WordRecord, LocalDateTime> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.defaulted(true), this, "", new LocalDateTimeConverter());

	/**
	 * The column <code>public.word.updated_date</code>.
	 */
	public final TableField<WordRecord, LocalDateTime> UPDATED_DATE = createField("updated_date", org.jooq.impl.SQLDataType.TIMESTAMP.defaulted(true), this, "", new LocalDateTimeConverter());

	/**
	 * Create a <code>public.word</code> table reference
	 */
	public Word() {
		this("word", null);
	}

	/**
	 * Create an aliased <code>public.word</code> table reference
	 */
	public Word(String alias) {
		this(alias, WORD);
	}

	private Word(String alias, Table<WordRecord> aliased) {
		this(alias, aliased, null);
	}

	private Word(String alias, Table<WordRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "Character-related word data.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<WordRecord> getPrimaryKey() {
		return Keys.WORD_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<WordRecord>> getKeys() {
		return Arrays.<UniqueKey<WordRecord>>asList(Keys.WORD_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Word as(String alias) {
		return new Word(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Word rename(String name) {
		return new Word(name, null);
	}
}
