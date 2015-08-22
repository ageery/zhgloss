package com.zhgloss.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zhgloss.domain.external.TranscriptionSystemInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSettings implements Serializable {

	public static final String JSON_TRANSCRIPTION_SYSTEM_CODE = "transcriptionSystemCode";
	public static final String JSON_CHAR_TYPE = "charType";
	
	@JsonProperty(JSON_TRANSCRIPTION_SYSTEM_CODE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="code")
	@JsonIdentityReference(alwaysAsId = true) 
	private TranscriptionSystemInfo transcriptionSystem;
	
	@JsonProperty(JSON_CHAR_TYPE)
	private CharacterType characterType;
	
	public UserSettings() {
		super();
	}

	public TranscriptionSystemInfo getTranscriptionSystem() {
		return transcriptionSystem;
	}

	public void setTranscriptionSystem(TranscriptionSystemInfo transcriptionSystem) {
		this.transcriptionSystem = transcriptionSystem;
	}
	
	public UserSettings withTranscriptionSystem(TranscriptionSystemInfo transcriptionSystem) {
		setTranscriptionSystem(transcriptionSystem);
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
				.append("transcription system code", getTranscriptionSystem())
				.append("character type", getCharacterType())
				.toString();
	}
	
}
