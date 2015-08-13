/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables.records;


import com.zhgloss.repo.jooq.tables.CedictWordDef;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Definition-related word data from the CEDICT file.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CedictWordDefRecord extends UpdatableRecordImpl<CedictWordDefRecord> implements Record4<UUID, UUID, Integer, String> {

	private static final long serialVersionUID = 1157457854;

	/**
	 * Setter for <code>public.cedict_word_def.id</code>.
	 */
	public void setId(UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.cedict_word_def.id</code>.
	 */
	public UUID getId() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>public.cedict_word_def.word_id</code>.
	 */
	public void setWordId(UUID value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.cedict_word_def.word_id</code>.
	 */
	public UUID getWordId() {
		return (UUID) getValue(1);
	}

	/**
	 * Setter for <code>public.cedict_word_def.order_num</code>.
	 */
	public void setOrderNum(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.cedict_word_def.order_num</code>.
	 */
	public Integer getOrderNum() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>public.cedict_word_def.def</code>.
	 */
	public void setDef(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.cedict_word_def.def</code>.
	 */
	public String getDef() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<UUID> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<UUID, UUID, Integer, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<UUID, UUID, Integer, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field1() {
		return CedictWordDef.CEDICT_WORD_DEF.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field2() {
		return CedictWordDef.CEDICT_WORD_DEF.WORD_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return CedictWordDef.CEDICT_WORD_DEF.ORDER_NUM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return CedictWordDef.CEDICT_WORD_DEF.DEF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value2() {
		return getWordId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getOrderNum();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getDef();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWordDefRecord value1(UUID value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWordDefRecord value2(UUID value) {
		setWordId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWordDefRecord value3(Integer value) {
		setOrderNum(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWordDefRecord value4(String value) {
		setDef(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CedictWordDefRecord values(UUID value1, UUID value2, Integer value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CedictWordDefRecord
	 */
	public CedictWordDefRecord() {
		super(CedictWordDef.CEDICT_WORD_DEF);
	}

	/**
	 * Create a detached, initialised CedictWordDefRecord
	 */
	public CedictWordDefRecord(UUID id, UUID wordId, Integer orderNum, String def) {
		super(CedictWordDef.CEDICT_WORD_DEF);

		setValue(0, id);
		setValue(1, wordId);
		setValue(2, orderNum);
		setValue(3, def);
	}
}
