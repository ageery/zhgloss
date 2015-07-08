package com.zixinxi.domain;

public enum CharacterType {

	TRADITIONAL("trad"),
	SIMPLFIED("simp");
	
	private String dbValue;
	
	CharacterType(String dbValue) {
		this.dbValue = dbValue;
	}

	public String getDbValue() {
		return dbValue;
	}
	
}
