/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables;


import com.zhgloss.repo.jooq.Keys;
import com.zhgloss.repo.jooq.Public;
import com.zhgloss.repo.jooq.tables.records.TranscriptionPointRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * Toned transcription syllable info for a transcription system.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TranscriptionPoint extends TableImpl<TranscriptionPointRecord> {

	private static final long serialVersionUID = 1978599357;

	/**
	 * The reference instance of <code>public.transcription_point</code>
	 */
	public static final TranscriptionPoint TRANSCRIPTION_POINT = new TranscriptionPoint();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TranscriptionPointRecord> getRecordType() {
		return TranscriptionPointRecord.class;
	}

	/**
	 * The column <code>public.transcription_point.ts_code</code>.
	 */
	public final TableField<TranscriptionPointRecord, String> TS_CODE = createField("ts_code", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.transcription_point.pinyin_syllable</code>.
	 */
	public final TableField<TranscriptionPointRecord, String> PINYIN_SYLLABLE = createField("pinyin_syllable", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.transcription_point.tone</code>.
	 */
	public final TableField<TranscriptionPointRecord, Integer> TONE = createField("tone", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.transcription_point.untoned_representation</code>.
	 */
	public final TableField<TranscriptionPointRecord, String> UNTONED_REPRESENTATION = createField("untoned_representation", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>public.transcription_point.toned_representation</code>.
	 */
	public final TableField<TranscriptionPointRecord, String> TONED_REPRESENTATION = createField("toned_representation", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>public.transcription_point</code> table reference
	 */
	public TranscriptionPoint() {
		this("transcription_point", null);
	}

	/**
	 * Create an aliased <code>public.transcription_point</code> table reference
	 */
	public TranscriptionPoint(String alias) {
		this(alias, TRANSCRIPTION_POINT);
	}

	private TranscriptionPoint(String alias, Table<TranscriptionPointRecord> aliased) {
		this(alias, aliased, null);
	}

	private TranscriptionPoint(String alias, Table<TranscriptionPointRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "Toned transcription syllable info for a transcription system.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TranscriptionPointRecord> getPrimaryKey() {
		return Keys.TRANSCRIPTION_POINT_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TranscriptionPointRecord>> getKeys() {
		return Arrays.<UniqueKey<TranscriptionPointRecord>>asList(Keys.TRANSCRIPTION_POINT_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<TranscriptionPointRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<TranscriptionPointRecord, ?>>asList(Keys.TRANSCRIPTION_POINT__TRANSCRIPTION_POINT_TS_CODE_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TranscriptionPoint as(String alias) {
		return new TranscriptionPoint(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TranscriptionPoint rename(String name) {
		return new TranscriptionPoint(name, null);
	}
}
