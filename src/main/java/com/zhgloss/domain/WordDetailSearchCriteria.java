package com.zhgloss.domain;

import java.util.List;
import java.util.UUID;

public class WordDetailSearchCriteria {

	private UUID id;
	private List<CharacterInfo> characterInfoCriteria;
	private List<String> definitionCriteria;
	private Integer wordLength;
	
	public WordDetailSearchCriteria() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public WordDetailSearchCriteria withId(UUID id) {
		setId(id);
		return this;
	}

	public List<CharacterInfo> getCharacterInfoCriteria() {
		return characterInfoCriteria;
	}

	public void setCharacterInfoCriteria(List<CharacterInfo> characterInfoCriteria) {
		this.characterInfoCriteria = characterInfoCriteria;
	}
	
	public WordDetailSearchCriteria withCharacterInfoCriteria(List<CharacterInfo> characterInfoCriteria) {
		setCharacterInfoCriteria(characterInfoCriteria);
		return this;
	}

	public List<String> getDefinitionCriteria() {
		return definitionCriteria;
	}

	public void setDefinitionCriteria(List<String> definitionCriteria) {
		this.definitionCriteria = definitionCriteria;
	}
	
	public WordDetailSearchCriteria withDefinitionCriteria(List<String> definitionCriteria) {
		setDefinitionCriteria(definitionCriteria);
		return this;
	}

	public Integer getWordLength() {
		return wordLength;
	}

	public void setWordLength(Integer wordLength) {
		this.wordLength = wordLength;
	}
	
	public WordDetailSearchCriteria withWordLength(Integer wordLength) {
		setWordLength(wordLength);
		return this;
	}
	
}
