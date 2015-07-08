package com.zixinxi.web.wicket.content.lookup;

import java.io.Serializable;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class WordLookupCriteria implements Serializable {

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
