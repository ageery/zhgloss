package com.zhgloss.domain.external;

import static java.util.Optional.ofNullable;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zhgloss.domain.SerializableFunction;

public class TranscriptionSystemInfo implements Serializable {
	
	public static final String CODE_HANYU_PINYIN = "H";

	public static final SerializableFunction<TranscriptionSystemInfo, Optional<String>> FUNCTION_NAME = tsi -> ofNullable(tsi).flatMap(x -> ofNullable(x.getName()));
	
	private String code;
	private String name;
	private String shortName;
	
	public TranscriptionSystemInfo() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TranscriptionSystemInfo withCode(String code) {
		setCode(code);
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TranscriptionSystemInfo withName(String name) {
		setName(name);
		return this;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public TranscriptionSystemInfo withShortName(String shortName) {
		setShortName(shortName);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("code", getCode())
				.append("name", getName())
				.toString();
				
	}
	
}
