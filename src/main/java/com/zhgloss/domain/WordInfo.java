package com.zhgloss.domain;

import static com.zhgloss.domain.TranscriptionFormat.CAPITALIZED;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.text.WordUtils.capitalize;

import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class WordInfo {
	
	@JsonProperty
	private List<CharacterInfo> characters;
	
	public WordInfo(List<CharacterInfo> characters) {
		super();
		this.characters = characters;
	}

	public List<CharacterInfo> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterInfo> characters) {
		this.characters = characters;
	}
	
	public String getTraditionalCharacters() {
		return getCharacterStream()
				.map(CharacterInfo::getTraditional)
				.collect(joining(""));
	}
	
	public String getSimplifiedCharacters() {
		return getCharacterStream()
				.map(CharacterInfo::getSimplified)
				.collect(joining(""));
	}
	
	public String getPinyin() {
		return getCharacterStream()
				.map(ci -> {
					String pinyin = ci.getPinyin();
					if (CAPITALIZED.equals(ci.getFormat())) {
						pinyin = capitalize(pinyin);
					}
					Integer tone = ci.getTone();
					return pinyin + (tone == null ? "" : tone);
				})
				.collect(joining(" "));
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("traditional characters", getTraditionalCharacters())
			.add("simplified characters", getSimplifiedCharacters())
			.add("pinyin", getPinyin())
			.toString();
	}
	
	private Stream<CharacterInfo> getCharacterStream() {
		return characters == null ? Stream.empty() : characters.stream();
	}
	
}
