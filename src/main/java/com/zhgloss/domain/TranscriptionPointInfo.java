package com.zhgloss.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscriptionPointInfo implements Serializable {
	              
	@JsonProperty("toned-representation")
	private String tonedRepresentation;
	
	@JsonProperty("untoned-representation")
	private String untonedRepresentation;
	
	public TranscriptionPointInfo() {
		super();
	}

	public String getTonedRepresentation() {
		return tonedRepresentation;
	}

	public void setTonedRepresentation(String tonedRepresentation) {
		this.tonedRepresentation = tonedRepresentation;
	}

	public String getUntonedRepresentation() {
		return untonedRepresentation;
	}

	public void setUntonedRepresentation(String untonedRepresentation) {
		this.untonedRepresentation = untonedRepresentation;
	}
	
}
