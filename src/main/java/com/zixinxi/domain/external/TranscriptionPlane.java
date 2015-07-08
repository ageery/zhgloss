package com.zixinxi.domain.external;

import java.io.Serializable;

import com.zixinxi.domain.SerializableFunction;

public class TranscriptionPlane implements Serializable {

	private String syllableName;
	private int tone;
	private SerializableFunction<String, String> tonedRepresentationMapper;

	public String getSyllableName() {
		return syllableName;
	}

	public void setSyllableName(String syllableName) {
		this.syllableName = syllableName;
	}
	
	public TranscriptionPlane withSyllableName(String syllableName) {
		setSyllableName(syllableName);
		return this;
	}

	public int getTone() {
		return tone;
	}

	public void setTone(int tone) {
		this.tone = tone;
	}
	
	public TranscriptionPlane withTone(int tone) {
		setTone(tone);
		return this;
	}

	public SerializableFunction<String, String> getTonedRepresentationMapper() {
		return tonedRepresentationMapper;
	}

	public void setTonedRepresentationMapper(SerializableFunction<String, String> tonedRepresentationMapper) {
		this.tonedRepresentationMapper = tonedRepresentationMapper;
	}
	
	public TranscriptionPlane withTonedRepresentationMapper(SerializableFunction<String, String> tonedRepresentationMapper) {
		setTonedRepresentationMapper(tonedRepresentationMapper);
		return this;
	}

}
