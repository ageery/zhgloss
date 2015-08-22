package com.zhgloss.domain.external;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TranscriptionSystemInfo implements Serializable {
	
	public static final String CODE_HANYU_PINYIN = "H";

	private String code;
	private String name;
	
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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("code", getCode())
				.append("name", getName())
				.toString();
				
	}
	
}
