package com.zhgloss.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterTranscription {

	@JsonProperty("untoned-syllable")
	private String untonedSyllable;

	@JsonProperty("toned-syllable")
	private String tonedSyllable;

	@JsonProperty("tone")
	private Integer tone;

	@JsonProperty("suffix")
	private Boolean suffix;

	@JsonProperty("suffix-toned-syllable")
	private String suffixTonedSyllable;

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

    public Boolean getSuffix() {
        return suffix;
    }

    public void setSuffix(Boolean suffix) {
        this.suffix = suffix;
    }

    public String getSuffixTonedSyllable() {
        return suffixTonedSyllable;
    }

    public void setSuffixTonedSyllable(String suffixTonedSyllable) {
        this.suffixTonedSyllable = suffixTonedSyllable;
    }

}
