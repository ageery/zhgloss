/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables;


import com.zhgloss.repo.jooq.Keys;
import com.zhgloss.repo.jooq.Public;
import com.zhgloss.repo.jooq.tables.records.CedictWordRecord;

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
 * Character-related word data from the CEDICT file.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CedictWord extends TableImpl<CedictWordRecord> {

	private static final long serialVersionUID = 859808469;

	/**
	 * The reference instance of <code>public.cedict_word</code>
	 */
	public static final CedictWord CEDICT_WORD = new CedictWord();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<CedictWordRecord> getRecordType() {
		return CedictWordRecord.class;
	}

	/**
	 * The column <code>public.cedict_word.id</code>.
	 */
	public final TableField<CedictWordRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>public.cedict_word.trad_chars</code>.
	 */
	public final TableField<CedictWordRecord, String> TRAD_CHARS = createField("trad_chars", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.cedict_word.simp_chars</code>.
	 */
	public final TableField<CedictWordRecord, String> SIMP_CHARS = createField("simp_chars", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.cedict_word.pinyin</code>.
	 */
	public final TableField<CedictWordRecord, String> PINYIN = createField("pinyin", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * Create a <code>public.cedict_word</code> table reference
	 */
	public CedictWord() {
		this("cedict_word", null);
	}

	/**
	 * Create an aliased <code>public.cedict_word</code> table reference
	 */
	public CedictWord(String alias) {
		this(alias, CEDICT_WORD);
	}

	private CedictWord(String alias, Table<CedictWordRecord> aliased) {
		this(alias, aliased, null);
	}

	private CedictWord(String alias, Table<CedictWordRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "Character-related word data from the CEDICT file.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<CedictWordRecord> getPrimaryKey() {
		return Keys.CEDICT_WORD_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<CedictWordRecord>> getKeys() {
		return Arrays.<UniqueKey<CedictWordRecord>>asList(Keys.CEDICT_WORD_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWord as(String alias) {
		return new CedictWord(alias, this);
	}

	/**
	 * Rename this table
	 */
	public CedictWord rename(String name) {
		return new CedictWord(name, null);
	}
}
