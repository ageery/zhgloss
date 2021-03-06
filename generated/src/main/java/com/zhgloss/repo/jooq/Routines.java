/**
 * This class is generated by jOOQ
 */
package com.zhgloss.repo.jooq;


import com.zhgloss.domain.WordInfo;
import com.zhgloss.repo.jooq.routines.FindLongestWordPrefix;
import com.zhgloss.repo.jooq.routines.FindLongestWordPrefixNullable;
import com.zhgloss.repo.jooq.routines.JsonbArrayAttrValuesToArr;
import com.zhgloss.repo.jooq.routines.JsonbArrayAttrValuesToString;
import com.zhgloss.repo.jooq.routines.MakeTranscriptionJson;
import com.zhgloss.repo.jooq.routines.MakeWordInfo;
import com.zhgloss.repo.jooq.routines.MakeWordJson;
import com.zhgloss.repo.jooq.routines.UpdateWordsFromCedictWords;
import com.zhgloss.repo.jooq.tables.FindSegments;
import com.zhgloss.repo.jooq.tables.FindSegmentsDetails;
import com.zhgloss.repo.jooq.tables.FindWords;
import com.zhgloss.repo.jooq.tables.FindWordsByDef;
import com.zhgloss.repo.jooq.tables.GetCedictWordParts;
import com.zhgloss.repo.jooq.tables.MakePrefixes;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Field;


/**
 * Convenience access to all stored procedures and functions in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

	/**
	 * Call <code>public.find_longest_word_prefix</code>
	 */
	public static String findLongestWordPrefix(Configuration configuration, String t, String ccType, Boolean maxUnmatched) {
		FindLongestWordPrefix f = new FindLongestWordPrefix();
		f.setT(t);
		f.setCcType(ccType);
		f.setMaxUnmatched(maxUnmatched);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.find_longest_word_prefix</code> as a field
	 */
	public static Field<String> findLongestWordPrefix(String t, String ccType, Boolean maxUnmatched) {
		FindLongestWordPrefix f = new FindLongestWordPrefix();
		f.setT(t);
		f.setCcType(ccType);
		f.setMaxUnmatched(maxUnmatched);

		return f.asField();
	}

	/**
	 * Get <code>public.find_longest_word_prefix</code> as a field
	 */
	public static Field<String> findLongestWordPrefix(Field<String> t, Field<String> ccType, Field<Boolean> maxUnmatched) {
		FindLongestWordPrefix f = new FindLongestWordPrefix();
		f.setT(t);
		f.setCcType(ccType);
		f.setMaxUnmatched(maxUnmatched);

		return f.asField();
	}

	/**
	 * Call <code>public.find_longest_word_prefix_nullable</code>
	 */
	public static String findLongestWordPrefixNullable(Configuration configuration, String t, String ccType) {
		FindLongestWordPrefixNullable f = new FindLongestWordPrefixNullable();
		f.setT(t);
		f.setCcType(ccType);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.find_longest_word_prefix_nullable</code> as a field
	 */
	public static Field<String> findLongestWordPrefixNullable(String t, String ccType) {
		FindLongestWordPrefixNullable f = new FindLongestWordPrefixNullable();
		f.setT(t);
		f.setCcType(ccType);

		return f.asField();
	}

	/**
	 * Get <code>public.find_longest_word_prefix_nullable</code> as a field
	 */
	public static Field<String> findLongestWordPrefixNullable(Field<String> t, Field<String> ccType) {
		FindLongestWordPrefixNullable f = new FindLongestWordPrefixNullable();
		f.setT(t);
		f.setCcType(ccType);

		return f.asField();
	}

	/**
	 * Call <code>public.jsonb_array_attr_values_to_arr</code>
	 */
	public static String[] jsonbArrayAttrValuesToArr(Configuration configuration, WordInfo jsonbArr, String attrName) {
		JsonbArrayAttrValuesToArr f = new JsonbArrayAttrValuesToArr();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.jsonb_array_attr_values_to_arr</code> as a field
	 */
	public static Field<String[]> jsonbArrayAttrValuesToArr(WordInfo jsonbArr, String attrName) {
		JsonbArrayAttrValuesToArr f = new JsonbArrayAttrValuesToArr();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);

		return f.asField();
	}

	/**
	 * Get <code>public.jsonb_array_attr_values_to_arr</code> as a field
	 */
	public static Field<String[]> jsonbArrayAttrValuesToArr(Field<WordInfo> jsonbArr, Field<String> attrName) {
		JsonbArrayAttrValuesToArr f = new JsonbArrayAttrValuesToArr();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);

		return f.asField();
	}

	/**
	 * Call <code>public.jsonb_array_attr_values_to_string</code>
	 */
	public static String jsonbArrayAttrValuesToString(Configuration configuration, WordInfo jsonbArr, String attrName, String separator) {
		JsonbArrayAttrValuesToString f = new JsonbArrayAttrValuesToString();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);
		f.setSeparator(separator);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.jsonb_array_attr_values_to_string</code> as a field
	 */
	public static Field<String> jsonbArrayAttrValuesToString(WordInfo jsonbArr, String attrName, String separator) {
		JsonbArrayAttrValuesToString f = new JsonbArrayAttrValuesToString();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);
		f.setSeparator(separator);

		return f.asField();
	}

	/**
	 * Get <code>public.jsonb_array_attr_values_to_string</code> as a field
	 */
	public static Field<String> jsonbArrayAttrValuesToString(Field<WordInfo> jsonbArr, Field<String> attrName, Field<String> separator) {
		JsonbArrayAttrValuesToString f = new JsonbArrayAttrValuesToString();
		f.setJsonbArr(jsonbArr);
		f.setAttrName(attrName);
		f.setSeparator(separator);

		return f.asField();
	}

	/**
	 * Call <code>public.make_transcription_json</code>
	 */
	public static Object makeTranscriptionJson(Configuration configuration, WordInfo wordInfo, String code) {
		MakeTranscriptionJson f = new MakeTranscriptionJson();
		f.setWordInfo(wordInfo);
		f.setCode(code);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.make_transcription_json</code> as a field
	 */
	public static Field<Object> makeTranscriptionJson(WordInfo wordInfo, String code) {
		MakeTranscriptionJson f = new MakeTranscriptionJson();
		f.setWordInfo(wordInfo);
		f.setCode(code);

		return f.asField();
	}

	/**
	 * Get <code>public.make_transcription_json</code> as a field
	 */
	public static Field<Object> makeTranscriptionJson(Field<WordInfo> wordInfo, Field<String> code) {
		MakeTranscriptionJson f = new MakeTranscriptionJson();
		f.setWordInfo(wordInfo);
		f.setCode(code);

		return f.asField();
	}

	/**
	 * Call <code>public.make_word_info</code>
	 */
	public static Object makeWordInfo(Configuration configuration, Object w) {
		MakeWordInfo f = new MakeWordInfo();
		f.setW(w);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.make_word_info</code> as a field
	 */
	public static Field<Object> makeWordInfo(Object w) {
		MakeWordInfo f = new MakeWordInfo();
		f.setW(w);

		return f.asField();
	}

	/**
	 * Get <code>public.make_word_info</code> as a field
	 */
	public static Field<Object> makeWordInfo(Field<Object> w) {
		MakeWordInfo f = new MakeWordInfo();
		f.setW(w);

		return f.asField();
	}

	/**
	 * Call <code>public.make_word_json</code>
	 */
	public static Object makeWordJson(Configuration configuration, WordInfo wordInfo, String[] defs, String tsCode) {
		MakeWordJson f = new MakeWordJson();
		f.setWordInfo(wordInfo);
		f.setDefs(defs);
		f.setTsCode(tsCode);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.make_word_json</code> as a field
	 */
	public static Field<Object> makeWordJson(WordInfo wordInfo, String[] defs, String tsCode) {
		MakeWordJson f = new MakeWordJson();
		f.setWordInfo(wordInfo);
		f.setDefs(defs);
		f.setTsCode(tsCode);

		return f.asField();
	}

	/**
	 * Get <code>public.make_word_json</code> as a field
	 */
	public static Field<Object> makeWordJson(Field<WordInfo> wordInfo, Field<String[]> defs, Field<String> tsCode) {
		MakeWordJson f = new MakeWordJson();
		f.setWordInfo(wordInfo);
		f.setDefs(defs);
		f.setTsCode(tsCode);

		return f.asField();
	}

	/**
	 * Call <code>public.update_words_from_cedict_words</code>
	 */
	public static Integer updateWordsFromCedictWords(Configuration configuration) {
		UpdateWordsFromCedictWords f = new UpdateWordsFromCedictWords();

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.update_words_from_cedict_words</code> as a field
	 */
	public static Field<Integer> updateWordsFromCedictWords() {
		UpdateWordsFromCedictWords f = new UpdateWordsFromCedictWords();

		return f.asField();
	}

	/**
	 * Get <code>public.find_segments</code> as a field
	 */
	public static FindSegments findSegments(String t, String ccType, Integer maxSegLen, Boolean maxUnmatched) {
		return FindSegments.FIND_SEGMENTS.call(t, ccType, maxSegLen, maxUnmatched);
	}

	/**
	 * Get <code>public.find_segments</code> as a field
	 */
	public static FindSegments findSegments(Field<String> t, Field<String> ccType, Field<Integer> maxSegLen, Field<Boolean> maxUnmatched) {
		return FindSegments.FIND_SEGMENTS.call(t, ccType, maxSegLen, maxUnmatched);
	}

	/**
	 * Get <code>public.find_segments_details</code> as a field
	 */
	public static FindSegmentsDetails findSegmentsDetails(String txt, String ccType, String tsCode, Integer maxSegLen, Boolean maxUnmatched) {
		return FindSegmentsDetails.FIND_SEGMENTS_DETAILS.call(txt, ccType, tsCode, maxSegLen, maxUnmatched);
	}

	/**
	 * Get <code>public.find_segments_details</code> as a field
	 */
	public static FindSegmentsDetails findSegmentsDetails(Field<String> txt, Field<String> ccType, Field<String> tsCode, Field<Integer> maxSegLen, Field<Boolean> maxUnmatched) {
		return FindSegmentsDetails.FIND_SEGMENTS_DETAILS.call(txt, ccType, tsCode, maxSegLen, maxUnmatched);
	}

	/**
	 * Get <code>public.find_words</code> as a field
	 */
	public static FindWords findWords(String jsonArrSearch, String tsCode) {
		return FindWords.FIND_WORDS.call(jsonArrSearch, tsCode);
	}

	/**
	 * Get <code>public.find_words</code> as a field
	 */
	public static FindWords findWords(Field<String> jsonArrSearch, Field<String> tsCode) {
		return FindWords.FIND_WORDS.call(jsonArrSearch, tsCode);
	}

	/**
	 * Get <code>public.find_words_by_def</code> as a field
	 */
	public static FindWordsByDef findWordsByDef(String tsquerySearch, String tsCode) {
		return FindWordsByDef.FIND_WORDS_BY_DEF.call(tsquerySearch, tsCode);
	}

	/**
	 * Get <code>public.find_words_by_def</code> as a field
	 */
	public static FindWordsByDef findWordsByDef(Field<String> tsquerySearch, Field<String> tsCode) {
		return FindWordsByDef.FIND_WORDS_BY_DEF.call(tsquerySearch, tsCode);
	}

	/**
	 * Get <code>public.get_cedict_word_parts</code> as a field
	 */
	public static GetCedictWordParts getCedictWordParts(Object wi) {
		return GetCedictWordParts.GET_CEDICT_WORD_PARTS.call(wi);
	}

	/**
	 * Get <code>public.get_cedict_word_parts</code> as a field
	 */
	public static GetCedictWordParts getCedictWordParts(Field<Object> wi) {
		return GetCedictWordParts.GET_CEDICT_WORD_PARTS.call(wi);
	}

	/**
	 * Get <code>public.make_prefixes</code> as a field
	 */
	public static MakePrefixes makePrefixes(String txt) {
		return MakePrefixes.MAKE_PREFIXES.call(txt);
	}

	/**
	 * Get <code>public.make_prefixes</code> as a field
	 */
	public static MakePrefixes makePrefixes(Field<String> txt) {
		return MakePrefixes.MAKE_PREFIXES.call(txt);
	}
}
