/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.tables;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import com.zhgloss.repo.jooq.Public;
import com.zhgloss.repo.jooq.tables.records.MakePrefixesRecord;


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
public class MakePrefixes extends TableImpl<MakePrefixesRecord> {

	private static final long serialVersionUID = 1410937832;

	/**
	 * The reference instance of <code>public.make_prefixes</code>
	 */
	public static final MakePrefixes MAKE_PREFIXES = new MakePrefixes();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<MakePrefixesRecord> getRecordType() {
		return MakePrefixesRecord.class;
	}

	/**
	 * The column <code>public.make_prefixes.make_prefixes</code>.
	 */
	public final TableField<MakePrefixesRecord, String> MAKE_PREFIXES_ = createField("make_prefixes", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>public.make_prefixes</code> table reference
	 */
	public MakePrefixes() {
		this("make_prefixes", null);
	}

	/**
	 * Create an aliased <code>public.make_prefixes</code> table reference
	 */
	public MakePrefixes(String alias) {
		this(alias, MAKE_PREFIXES);
	}

	private MakePrefixes(String alias, Table<MakePrefixesRecord> aliased) {
		this(alias, aliased, new Field[1]);
	}

	private MakePrefixes(String alias, Table<MakePrefixesRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MakePrefixes as(String alias) {
		return new MakePrefixes(alias, this, parameters);
	}

	/**
	 * Rename this table
	 */
	public MakePrefixes rename(String name) {
		return new MakePrefixes(name, null, parameters);
	}

	/**
	 * Call this table-valued function
	 */
	public MakePrefixes call(String txt) {
		return new MakePrefixes(getName(), null, new Field[] { DSL.val(txt) });
	}

	/**
	 * Call this table-valued function
	 */
	public MakePrefixes call(Field<String> txt) {
		return new MakePrefixes(getName(), null, new Field[] { txt });
	}
}