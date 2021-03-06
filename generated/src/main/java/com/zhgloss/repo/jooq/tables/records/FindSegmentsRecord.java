/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables.records;


import com.zhgloss.repo.jooq.tables.FindSegments;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class FindSegmentsRecord extends TableRecordImpl<FindSegmentsRecord> implements Record2<String, Integer> {

	private static final long serialVersionUID = 1019291033;

	/**
	 * Setter for <code>public.find_segments.chars</code>.
	 */
	public void setChars(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.find_segments.chars</code>.
	 */
	public String getChars() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>public.find_segments.order_num</code>.
	 */
	public void setOrderNum(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.find_segments.order_num</code>.
	 */
	public Integer getOrderNum() {
		return (Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<String, Integer> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<String, Integer> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return FindSegments.FIND_SEGMENTS.CHARS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return FindSegments.FIND_SEGMENTS.ORDER_NUM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getChars();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getOrderNum();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FindSegmentsRecord value1(String value) {
		setChars(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FindSegmentsRecord value2(Integer value) {
		setOrderNum(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FindSegmentsRecord values(String value1, Integer value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached FindSegmentsRecord
	 */
	public FindSegmentsRecord() {
		super(FindSegments.FIND_SEGMENTS);
	}

	/**
	 * Create a detached, initialised FindSegmentsRecord
	 */
	public FindSegmentsRecord(String chars, Integer orderNum) {
		super(FindSegments.FIND_SEGMENTS);

		setValue(0, chars);
		setValue(1, orderNum);
	}
}
