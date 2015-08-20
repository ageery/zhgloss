package com.zhgloss.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSettings implements Serializable {

	@JsonProperty("tsCode")
	private String transcriptionSystemCode;
	
	@JsonProperty("charType")
	private CharacterType characterType;
	
	public UserSettings() {
		super();
	}

	public String getTranscriptionSystemCode() {
		return transcriptionSystemCode;
	}

	public void setTranscriptionSystemCode(String transcriptionSystemCode) {
		this.transcriptionSystemCode = transcriptionSystemCode;
	}
	
	public UserSettings withTranscriptionSystemCode(String transcriptionSystemCode) {
		setTranscriptionSystemCode(transcriptionSystemCode);
		return this;
	}

	public CharacterType getCharacterType() {
		return characterType;
	}

	public void setCharacterType(CharacterType characterType) {
		this.characterType = characterType;
	}

	public UserSettings withCharacterType(CharacterType characterType) {
		setCharacterType(characterType);
		return this;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("transcription system code", getTranscriptionSystemCode())
				.append("character type", getCharacterType())
				.toString();
	}
	
}
