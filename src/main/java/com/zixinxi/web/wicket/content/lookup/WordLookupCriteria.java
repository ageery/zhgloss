package com.zixinxi.web.wicket.content.lookup;

import static com.zixinxi.domain.SerializableProperty.of;

import java.io.Serializable;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.zixinxi.domain.SerializableProperty;

public class WordLookupCriteria implements Serializable {
	
	public static final SerializableProperty<WordLookupCriteria, String> PROPERTY_TRADITIONAL_CHARACTERS = of(WordLookupCriteria::getTraditionalCharacters, WordLookupCriteria::setTraditionalCharacters);
	public static final SerializableProperty<WordLookupCriteria, String> PROPERTY_SIMPLIFIED_CHARACTERS = of(WordLookupCriteria::getSimplifiedCharacters, WordLookupCriteria::setSimplifiedCharacters);
	public static final SerializableProperty<WordLookupCriteria, String> PROPERTY_PINYIN = of(WordLookupCriteria::getPinyin, WordLookupCriteria::setPinyin);
	public static final SerializableProperty<WordLookupCriteria, String> PROPERTY_DEFINITION = of(WordLookupCriteria::getDefinition, WordLookupCriteria::setDefinition);
	
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
