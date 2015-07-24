package com.zhgloss.domain;

public enum CharacterType {

	TRADITIONAL("trad", "Traditional"),
	SIMPLFIED("simp", "Simplified");
	
	private String dbValue;
	private String displayValue;
	
	CharacterType(String dbValue, String displayValue) {
		this.dbValue = dbValue;
		this.displayValue = displayValue;
	}

	public String getDbValue() {
		return dbValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
}
