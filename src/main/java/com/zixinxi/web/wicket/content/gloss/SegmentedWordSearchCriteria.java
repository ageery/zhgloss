package com.zixinxi.web.wicket.content.gloss;

import java.io.Serializable;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.external.TranscriptionSystemInfo;

public class SegmentedWordSearchCriteria implements Serializable {
	
	private static final int DEFAULT_MAX_WORD_LEN = 10;
	
	private String text;
	private CharacterType characterType;
	private TranscriptionSystemInfo transcriptionSystem;
	private int maxWordLen;

	public SegmentedWordSearchCriteria() {
		super();
	}
	
	public SegmentedWordSearchCriteria(CharacterType characterType, TranscriptionSystemInfo transcriptionSystem) {
		super();
		this.characterType = characterType;
		this.transcriptionSystem = transcriptionSystem;
		this.maxWordLen = DEFAULT_MAX_WORD_LEN;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SegmentedWordSearchCriteria withText(String text) {
		setText(text);
		return this;
	}
	
	public CharacterType getCharacterType() {
		return characterType;
	}

	public void setCharacterType(CharacterType characterType) {
		this.characterType = characterType;
	}

	public SegmentedWordSearchCriteria withCharacterType(CharacterType characterType) {
		setCharacterType(characterType);
		return this;
	}
	
	public TranscriptionSystemInfo getTranscriptionSystem() {
		return transcriptionSystem;
	}

	public void setTranscriptionSystem(TranscriptionSystemInfo transcriptionSystem) {
		this.transcriptionSystem = transcriptionSystem;
	}

	public SegmentedWordSearchCriteria withTranscriptionSystem(TranscriptionSystemInfo transcriptionSystem) {
		this.transcriptionSystem = transcriptionSystem;
		return this;
	}

	public int getMaxWordLen() {
		return maxWordLen;
	}

	public void setMaxWordLen(int maxWordLen) {
		this.maxWordLen = maxWordLen;
	}
	
}
