package com.zhgloss.domain.external;

import java.io.Serializable;

import com.zhgloss.domain.Op;
import com.zhgloss.domain.SerializableFunction;

public class TranscriptionPlane implements Serializable {

	public static final SerializableFunction<TranscriptionPlane, Op<String>> FUNCTION_SYLLABLE_NAME = tp -> Op.of(tp).flatMap(TranscriptionPlane::getSyllableName);
	public static final SerializableFunction<TranscriptionPlane, Op<Integer>> FUNCTION_TONE = tp -> Op.of(tp).flatMap(TranscriptionPlane::getTone);
	
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
