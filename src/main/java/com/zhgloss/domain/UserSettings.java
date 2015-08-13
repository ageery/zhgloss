package com.zhgloss.domain;

import java.io.Serializable;

public class UserSettings implements Serializable {

	private String transcriptionSystemCode;
	private CharacterType characterType;
	private boolean simpleLayout;
	
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
	
	public boolean isSimpleLayout() {
		return simpleLayout;
	}

	public void setSimpleLayout(boolean simpleLayout) {
		this.simpleLayout = simpleLayout;
	}
	
	public UserSettings withSetSimpleLayout(boolean simpleLayout) {
		setSimpleLayout(simpleLayout);
		return this;
	}
	
}
