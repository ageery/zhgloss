package com.zixinxi.domain;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterInfo {

	@JsonProperty("trad")
	private String traditional;
	
	@JsonProperty("simp")
	private String simplified;
	
	@JsonProperty("pinyin")
	private String pinyin;
	
	@JsonProperty("tone")
	private Integer tone;
	
	@JsonProperty("format")
	private TranscriptionFormat format;
	
	@JsonProperty("index")
	private Integer index;
	
	public CharacterInfo() {
		super();
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}
	
	public CharacterInfo withTraditional(String traditional) {
		setTraditional(traditional);
		return this;
	}

	public String getSimplified() {
		return simplified;
	}

	public void setSimplified(String simplified) {
		this.simplified = simplified;
	}
	
	public CharacterInfo withSimplified(String simplified) {
		setSimplified(simplified);
		return this;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = StringUtils.lowerCase(pinyin);
	}

	public CharacterInfo withPinyin(String pinyin) {
		setPinyin(pinyin);
		return this;
	}
	
	public Integer getTone() {
		return tone;
	}

	public void setTone(Integer tone) {
		this.tone = tone;
	}
	
	public CharacterInfo withTone(Integer tone) {
		setTone(tone);
		return this;
	}

	public TranscriptionFormat getFormat() {
		return format;
	}

	public void setFormat(TranscriptionFormat format) {
		this.format = format;
	}
	
	public CharacterInfo withFormat(TranscriptionFormat format) {
		setFormat(format);
		return this;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public CharacterInfo withIndex(Integer index) {
		setIndex(index);
		return this;
	}

}
