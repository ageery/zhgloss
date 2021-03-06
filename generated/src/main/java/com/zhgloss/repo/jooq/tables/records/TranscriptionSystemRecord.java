/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables.records;


import com.zhgloss.repo.jooq.tables.TranscriptionSystem;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Phonetic systems for transcribing the Mandarin pronounciation of Chinese 
 * characters
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TranscriptionSystemRecord extends UpdatableRecordImpl<TranscriptionSystemRecord> implements Record3<String, String, String> {

	private static final long serialVersionUID = 867436702;

	/**
	 * Setter for <code>public.transcription_system.ts_code</code>.
	 */
	public void setTsCode(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.transcription_system.ts_code</code>.
	 */
	public String getTsCode() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>public.transcription_system.ts_name</code>.
	 */
	public void setTsName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.transcription_system.ts_name</code>.
	 */
	public String getTsName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.transcription_system.ts_short_name</code>.
	 */
	public void setTsShortName(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.transcription_system.ts_short_name</code>.
	 */
	public String getTsShortName() {
		return (String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
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
		return TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_SHORT_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getTsCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getTsName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getTsShortName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionSystemRecord value1(String value) {
		setTsCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionSystemRecord value2(String value) {
		setTsName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionSystemRecord value3(String value) {
		setTsShortName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionSystemRecord values(String value1, String value2, String value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TranscriptionSystemRecord
	 */
	public TranscriptionSystemRecord() {
		super(TranscriptionSystem.TRANSCRIPTION_SYSTEM);
	}

	/**
	 * Create a detached, initialised TranscriptionSystemRecord
	 */
	public TranscriptionSystemRecord(String tsCode, String tsName, String tsShortName) {
		super(TranscriptionSystem.TRANSCRIPTION_SYSTEM);

		setValue(0, tsCode);
		setValue(1, tsName);
		setValue(2, tsShortName);
	}
}
