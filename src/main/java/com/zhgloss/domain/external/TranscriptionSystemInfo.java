package com.zhgloss.domain.external;

import java.io.Serializable;

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
	
}
