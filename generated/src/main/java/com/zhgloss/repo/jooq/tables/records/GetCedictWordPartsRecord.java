/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables.records;


import com.zhgloss.repo.jooq.tables.GetCedictWordParts;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GetCedictWordPartsRecord extends TableRecordImpl<GetCedictWordPartsRecord> implements Record3<String, String, String> {

	private static final long serialVersionUID = 1921467222;

	/**
	 * Setter for <code>public.get_cedict_word_parts.trad_chars</code>.
	 */
	public void setTradChars(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.get_cedict_word_parts.trad_chars</code>.
	 */
	public String getTradChars() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>public.get_cedict_word_parts.simp_chars</code>.
	 */
	public void setSimpChars(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.get_cedict_word_parts.simp_chars</code>.
	 */
	public String getSimpChars() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.get_cedict_word_parts.pinyin</code>.
	 */
	public void setPinyin(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.get_cedict_word_parts.pinyin</code>.
	 */
	public String getPinyin() {
		return (String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<String, String, String> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<String, String, String> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return GetCedictWordParts.GET_CEDICT_WORD_PARTS.TRAD_CHARS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return GetCedictWordParts.GET_CEDICT_WORD_PARTS.SIMP_CHARS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return GetCedictWordParts.GET_CEDICT_WORD_PARTS.PINYIN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getTradChars();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getSimpChars();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getPinyin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetCedictWordPartsRecord value1(String value) {
		setTradChars(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetCedictWordPartsRecord value2(String value) {
		setSimpChars(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetCedictWordPartsRecord value3(String value) {
		setPinyin(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetCedictWordPartsRecord values(String value1, String value2, String value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached GetCedictWordPartsRecord
	 */
	public GetCedictWordPartsRecord() {
		super(GetCedictWordParts.GET_CEDICT_WORD_PARTS);
	}

	/**
	 * Create a detached, initialised GetCedictWordPartsRecord
	 */
	public GetCedictWordPartsRecord(String tradChars, String simpChars, String pinyin) {
		super(GetCedictWordParts.GET_CEDICT_WORD_PARTS);

		setValue(0, tradChars);
		setValue(1, simpChars);
		setValue(2, pinyin);
	}
}
