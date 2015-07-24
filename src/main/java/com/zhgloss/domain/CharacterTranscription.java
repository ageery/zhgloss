package com.zhgloss.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterTranscription {

	@JsonProperty("untoned-syllable")
	private String untonedSyllable;
	
	@JsonProperty("toned-syllable")
	private String tonedSyllable;
	
	@JsonProperty("tone")
	private Integer tone;
	
	public CharacterTranscription() {
		super();
	}

	public String getUntonedSyllable() {
		return untonedSyllable;
	}

	public void setUntonedSyllable(String untonedSyllable) {
		this.untonedSyllable = untonedSyllable;
	}

	public String getTonedSyllable() {
		return tonedSyllable;
	}

	public void setTonedSyllable(String tonedSyllable) {
		this.tonedSyllable = tonedSyllable;
	}

	public Integer getTone() {
		return tone;
	}

	public void setTone(Integer tone) {
		this.tone = tone;
	}
	
}
