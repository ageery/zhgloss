package com.zhgloss.unihan;

import java.util.Map;

public class UnihanInfo {

	private String character;
	
	private Map<String, String> properties;
	
	public UnihanInfo(String character, Map<String, String> properties) {
		super();
		this.character = character;
		this.properties = properties;
	}

	public String getCharacter() {
		return character;
	}

	public Map<String, String> getProperties() {
		return properties;
	}
	
}
