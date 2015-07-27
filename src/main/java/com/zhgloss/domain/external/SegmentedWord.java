package com.zhgloss.domain.external;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhgloss.domain.SerializableFunction;

public class SegmentedWord {
	
	public static final SerializableFunction<SegmentedWord, Optional<String>> FUNCTION_TEXT = sw -> ofNullable(sw).flatMap(x -> ofNullable(x.getText()));
	public static final SerializableFunction<SegmentedWord, Optional<List<WordParts>>> FUNCTION_WORDS = sw -> ofNullable(sw).flatMap(x -> ofNullable(x.getWords()));
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("words")
	private List<WordParts> words;
	
	public SegmentedWord() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SegmentedWord withText(String text) {
		setText(text);
		return this;
	}
	
	public List<WordParts> getWords() {
		return words;
	}

	public void setWords(List<WordParts> words) {
		this.words = words;
	}
	
	public SegmentedWord withWords(List<WordParts> words) {
		setWords(words);
		return this;
	}
	
	public Stream<WordParts> getWordStream() {
		return words == null ? Stream.empty() : words.stream();
	}
	
	public boolean hasDefinition() {
		return getWordStream().count() > 0;
	}
	
}
