/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq.routines;


import com.zhgloss.domain.WordInfo;
import com.zhgloss.jooq.WordInfoConverter;
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
public class MakeWordJson extends AbstractRoutine<Object> {

	private static final long serialVersionUID = 1579355002;

	/**
	 * The parameter <code>public.make_word_json.RETURN_VALUE</code>.
	 */
	public static final Parameter<Object> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.OTHER, false);

	/**
	 * The parameter <code>public.make_word_json.word_info</code>.
	 */
	public static final Parameter<WordInfo> WORD_INFO = createParameter("word_info", org.jooq.impl.DefaultDataType.getDefaultDataType("jsonb"), false, new WordInfoConverter());

	/**
	 * The parameter <code>public.make_word_json.defs</code>.
	 */
	public static final Parameter<String[]> DEFS = createParameter("defs", org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), false);

	/**
	 * The parameter <code>public.make_word_json.ts_code</code>.
	 */
	public static final Parameter<String> TS_CODE = createParameter("ts_code", org.jooq.impl.SQLDataType.CLOB, false);

	/**
	 * Create a new routine call instance
	 */
	public MakeWordJson() {
		super("make_word_json", Public.PUBLIC, org.jooq.impl.SQLDataType.OTHER);

		setReturnParameter(RETURN_VALUE);
		addInParameter(WORD_INFO);
		addInParameter(DEFS);
		addInParameter(TS_CODE);
	}

	/**
	 * Set the <code>word_info</code> parameter IN value to the routine
	 */
	public void setWordInfo(WordInfo value) {
		setValue(WORD_INFO, value);
	}

	/**
	 * Set the <code>word_info</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setWordInfo(Field<WordInfo> field) {
		setField(WORD_INFO, field);
	}

	/**
	 * Set the <code>defs</code> parameter IN value to the routine
	 */
	public void setDefs(String[] value) {
		setValue(DEFS, value);
	}

	/**
	 * Set the <code>defs</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setDefs(Field<String[]> field) {
		setField(DEFS, field);
	}

	/**
	 * Set the <code>ts_code</code> parameter IN value to the routine
	 */
	public void setTsCode(String value) {
		setValue(TS_CODE, value);
	}

	/**
	 * Set the <code>ts_code</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setTsCode(Field<String> field) {
		setField(TS_CODE, field);
	}
}
