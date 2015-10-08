package com.zhgloss.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class WordDetail implements Serializable {

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("simplified-characters")
	private String simplifiedCharacters;

	@JsonProperty("traditional-characters")
	private String traditionalCharacters;

	@JsonProperty("transcription")
	private List<CharacterTranscription> characterTranscriptions;

	@JsonProperty("definitions")
	private List<String> definitions;

	public WordDetail() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public WordDetail withId(UUID id) {
		setId(id);
		return this;
	}

	public String getSimplifiedCharacters() {
		return simplifiedCharacters;
	}

	public void setSimplifiedCharacters(String simplifiedCharacters) {
		this.simplifiedCharacters = simplifiedCharacters;
	}

	public WordDetail withSimplifiedCharacters(String simplifiedCharacters) {
		setSimplifiedCharacters(simplifiedCharacters);
		return this;
	}

	public String getTraditionalCharacters() {
		return traditionalCharacters;
	}

	public void setTraditionalCharacters(String traditionalCharacters) {
		this.traditionalCharacters = traditionalCharacters;
	}

	public WordDetail withTraditionalCharacters(String traditionalCharacters) {
		setTraditionalCharacters(traditionalCharacters);
		return this;
	}

	public List<CharacterTranscription> getCharacterTranscriptions() {
		return characterTranscriptions;
	}

	public void setCharacterTranscriptions(List<CharacterTranscription> characterTranscriptions) {
		this.characterTranscriptions = characterTranscriptions;
	}

	public WordDetail withCharacterTranscriptions(List<CharacterTranscription> characterTranscriptions) {
		setCharacterTranscriptions(characterTranscriptions);
		return this;
	}

	public List<String> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<String> definitions) {
		this.definitions = definitions;
	}

	public WordDetail withDefinitions(List<String> definitions) {
		setDefinitions(definitions);
		return this;
	}

	public String getTranscription() {
		return getCharacterTranscriptionStream()
		        .map(ct -> ct.getSuffixTonedSyllable() != null
		            ? Stream.of("", ct.getSuffixTonedSyllable())
		            : Stream.of(" ", ct.getTonedSyllable() != null
		                ? ct.getTonedSyllable()
		                : ct.getUntonedSyllable()))
				.flatMap(Function.identity())
				.collect(Collectors.joining(""))
				.trim();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("traditional characters", getTraditionalCharacters())
				.add("simplified characters", getSimplifiedCharacters())
				.add("transcription", getTranscription())
				.add("definitions", getDefinitionsStream().collect(Collectors.joining("; ", "[", "]")))
				.toString();
	}

	private Stream<CharacterTranscription> getCharacterTranscriptionStream() {
		return characterTranscriptions == null ? Stream.empty() : characterTranscriptions.stream();
	}

	private Stream<String> getDefinitionsStream() {
		return definitions == null ? Stream.empty() : definitions.stream();
	}

}
