package com.zhgloss.domain;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TranscriptionFormat {

	CAPITALIZED("capitalized");
	
	private static final Map<String, TranscriptionFormat> DB_VALUE_MAP = of(values()).collect(toMap(TranscriptionFormat::getDbValue, identity())); 
	
	private String dbValue;
	
	TranscriptionFormat(String dbValue) {
		this.dbValue = dbValue;
	}
	
	@JsonProperty
	public String getDbValue() {
		return dbValue;
	}
	
	@JsonCreator
	public static TranscriptionFormat forValue(String value) {
		return DB_VALUE_MAP.get(value);
	}
	
}
