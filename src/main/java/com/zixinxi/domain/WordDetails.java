package com.zixinxi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDetails {

	@JsonProperty
	private List<WordDetail> wordDetails;
	
	public WordDetails(List<WordDetail> wordDetails) {
		this.wordDetails = wordDetails;
	}

	public List<WordDetail> getWordDetails() {
		return wordDetails;
	}

	public void setWordDetails(List<WordDetail> wordDetails) {
		this.wordDetails = wordDetails;
	}
	
}
