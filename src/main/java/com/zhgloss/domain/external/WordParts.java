package com.zhgloss.domain.external;

import static java.util.Optional.ofNullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhgloss.domain.SerializableFunction;

public class WordParts implements Serializable {

	public static final SerializableFunction<WordParts, Optional<String>> FUNCTION_TRADITIONAL = wp -> ofNullable(wp).flatMap(x -> ofNullable(x.getTraditional()));
	public static final SerializableFunction<WordParts, Optional<String>> FUNCTION_SIMPLIFIED = wp -> ofNullable(wp).flatMap(x -> ofNullable(x.getSimplified()));
	public static final SerializableFunction<WordParts, Optional<String>> FUNCTION_TRANSCRIPTION = wp -> ofNullable(wp).flatMap(x -> ofNullable(x.getTranscription()));
	public static final SerializableFunction<WordParts, Optional<List<String>>> FUNCTION_DEFINITIONS = wp -> ofNullable(wp).flatMap(x -> ofNullable(x.getDefinitions()));
	
	@JsonProperty("id")
	private UUID id;
	
	@JsonProperty("trad")
	private String traditional;
	
	@JsonProperty("simp")
	private String simplified;
	
	@JsonProperty("transcription")
	private String transcription;
	
	@JsonProperty("defs")
	private List<String> definitions;
	
	public WordParts() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public WordParts withId(UUID id) {
		setId(id);
		return this;
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}
	
	public WordParts withTraditional(String traditional) {
		setTraditional(traditional);
		return this;
	}

	public String getSimplified() {
		return simplified;
	}

	public void setSimplified(String simplified) {
		this.simplified = simplified;
	}
	
	public WordParts withSimplified(String simplified) {
		setSimplified(simplified);
		return this;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}
	
	public WordParts withTranscription(String transcription) {
		setTranscription(transcription);
		return this;
	}

	public List<String> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<String> definitions) {
		this.definitions = definitions;
	}
	
	public WordParts withDefinitions(List<String> definitions) {
		setDefinitions(definitions);
		return this;
	}

	public Stream<String> getDefinitionStream() {
		return definitions == null ? Stream.empty() : definitions.stream();
	}
	
}
