/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.routines;


import com.zhgloss.repo.jooq.Public;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;


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
public class FindLongestWordPrefix extends AbstractRoutine<String> {

	private static final long serialVersionUID = 618550523;

	/**
	 * The parameter <code>public.find_longest_word_prefix.RETURN_VALUE</code>.
	 */
	public static final Parameter<String> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.CLOB, false);

	/**
	 * The parameter <code>public.find_longest_word_prefix.t</code>.
	 */
	public static final Parameter<String> T = createParameter("t", org.jooq.impl.SQLDataType.CLOB, false);

	/**
	 * The parameter <code>public.find_longest_word_prefix.cc_type</code>.
	 */
	public static final Parameter<String> CC_TYPE = createParameter("cc_type", org.jooq.impl.SQLDataType.CLOB, false);

	/**
	 * The parameter <code>public.find_longest_word_prefix.max_unmatched</code>.
	 */
	public static final Parameter<Boolean> MAX_UNMATCHED = createParameter("max_unmatched", org.jooq.impl.SQLDataType.BOOLEAN, false);

	/**
	 * Create a new routine call instance
	 */
	public FindLongestWordPrefix() {
		super("find_longest_word_prefix", Public.PUBLIC, org.jooq.impl.SQLDataType.CLOB);

		setReturnParameter(RETURN_VALUE);
		addInParameter(T);
		addInParameter(CC_TYPE);
		addInParameter(MAX_UNMATCHED);
	}

	/**
	 * Set the <code>t</code> parameter IN value to the routine
	 */
	public void setT(String value) {
		setValue(T, value);
	}

	/**
	 * Set the <code>t</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setT(Field<String> field) {
		setField(T, field);
	}

	/**
	 * Set the <code>cc_type</code> parameter IN value to the routine
	 */
	public void setCcType(String value) {
		setValue(CC_TYPE, value);
	}

	/**
	 * Set the <code>cc_type</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setCcType(Field<String> field) {
		setField(CC_TYPE, field);
	}

	/**
	 * Set the <code>max_unmatched</code> parameter IN value to the routine
	 */
	public void setMaxUnmatched(Boolean value) {
		setValue(MAX_UNMATCHED, value);
	}

	/**
	 * Set the <code>max_unmatched</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setMaxUnmatched(Field<Boolean> field) {
		setField(MAX_UNMATCHED, field);
	}
}
