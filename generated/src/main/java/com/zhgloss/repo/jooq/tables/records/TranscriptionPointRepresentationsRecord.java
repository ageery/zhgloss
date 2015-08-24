/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables.records;


import com.zhgloss.domain.TranscriptionPointRepresentationInfo;
import com.zhgloss.repo.jooq.tables.TranscriptionPointRepresentations;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * Transcription points with representation info for each system in JSON.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TranscriptionPointRepresentationsRecord extends TableRecordImpl<TranscriptionPointRepresentationsRecord> implements Record3<String, Integer, TranscriptionPointRepresentationInfo> {

	private static final long serialVersionUID = -857392542;

	/**
	 * Setter for <code>public.transcription_point_representations.pinyin_syllable</code>.
	 */
	public void setPinyinSyllable(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.transcription_point_representations.pinyin_syllable</code>.
	 */
	public String getPinyinSyllable() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>public.transcription_point_representations.tone</code>.
	 */
	public void setTone(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.transcription_point_representations.tone</code>.
	 */
	public Integer getTone() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.transcription_point_representations.system_info</code>.
	 */
	public void setSystemInfo(TranscriptionPointRepresentationInfo value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.transcription_point_representations.system_info</code>.
	 */
	public TranscriptionPointRepresentationInfo getSystemInfo() {
		return (TranscriptionPointRepresentationInfo) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<String, Integer, TranscriptionPointRepresentationInfo> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<String, Integer, TranscriptionPointRepresentationInfo> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return TranscriptionPointRepresentations.TRANSCRIPTION_POINT_REPRESENTATIONS.PINYIN_SYLLABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return TranscriptionPointRepresentations.TRANSCRIPTION_POINT_REPRESENTATIONS.TONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<TranscriptionPointRepresentationInfo> field3() {
		return TranscriptionPointRepresentations.TRANSCRIPTION_POINT_REPRESENTATIONS.SYSTEM_INFO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getPinyinSyllable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getTone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPointRepresentationInfo value3() {
		return getSystemInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPointRepresentationsRecord value1(String value) {
		setPinyinSyllable(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPointRepresentationsRecord value2(Integer value) {
		setTone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPointRepresentationsRecord value3(TranscriptionPointRepresentationInfo value) {
		setSystemInfo(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPointRepresentationsRecord values(String value1, Integer value2, TranscriptionPointRepresentationInfo value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TranscriptionPointRepresentationsRecord
	 */
	public TranscriptionPointRepresentationsRecord() {
		super(TranscriptionPointRepresentations.TRANSCRIPTION_POINT_REPRESENTATIONS);
	}

	/**
	 * Create a detached, initialised TranscriptionPointRepresentationsRecord
	 */
	public TranscriptionPointRepresentationsRecord(String pinyinSyllable, Integer tone, TranscriptionPointRepresentationInfo systemInfo) {
		super(TranscriptionPointRepresentations.TRANSCRIPTION_POINT_REPRESENTATIONS);

		setValue(0, pinyinSyllable);
		setValue(1, tone);
		setValue(2, systemInfo);
	}
}