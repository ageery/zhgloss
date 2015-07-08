package com.zixinxi.domain.external;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SegmentedWord {

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
	
}
