package com.zixinxi.web.wicket.content.lookup;

import static com.zixinxi.domain.Op.of;

import java.io.Serializable;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.zixinxi.domain.Op;
import com.zixinxi.domain.SerializableFunction;

public class WordLookupCriteria implements Serializable {
	
	public static final SerializableFunction<WordLookupCriteria, Op<String>> FUNCTION_TRADITIONAL = wlc -> of(wlc).flatMap(WordLookupCriteria::getTraditionalCharacters);
	public static final SerializableFunction<WordLookupCriteria, Op<String>> FUNCTION_SIMPLIFIED = wlc -> of(wlc).flatMap(WordLookupCriteria::getSimplifiedCharacters);
	public static final SerializableFunction<WordLookupCriteria, Op<String>> FUNCTION_PINYIN = wlc -> of(wlc).flatMap(WordLookupCriteria::getPinyin);
	public static final SerializableFunction<WordLookupCriteria, Op<String>> FUNCTION_DEFINITION = wlc -> of(wlc).flatMap(WordLookupCriteria::getDefinition);
	
	private String traditionalCharacters;
	private String simplifiedCharacters;
	private String pinyin;
	private String definition;
	
	public WordLookupCriteria() {
		super();
	}

	public String getTraditionalCharacters() {
		return traditionalCharacters;
	}

	public void setTraditionalCharacters(String traditionalCharacters) {
		this.traditionalCharacters = traditionalCharacters;
	}

	public String getSimplifiedCharacters() {
		return simplifiedCharacters;
	}

	public void setSimplifiedCharacters(String simplifiedCharacters) {
		this.simplifiedCharacters = simplifiedCharacters;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public WordLookupCriteria withPinyin(String pinyin) {
		setPinyin(pinyin);
		return this;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public boolean isEmpty() {
		return Stream.of(traditionalCharacters, simplifiedCharacters, pinyin, definition)
				.map(s -> s == null ? null : s.replace("*", ""))
				.map(StringUtils::isEmpty)
				.reduce((a,b) -> a && b)
				.orElse(false);
	}
	
}
