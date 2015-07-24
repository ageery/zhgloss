package com.zhgloss.domain;

import java.io.Serializable;
import java.util.Map;

public class TranscriptionPointRepresentationInfo implements Serializable {
	
	private Map<String, TranscriptionPointInfo> systemTranscriptions;

	public TranscriptionPointRepresentationInfo() {
		super();
	}
	
	public TranscriptionPointRepresentationInfo(Map<String, TranscriptionPointInfo> systemTranscriptions) {
		super();
		this.systemTranscriptions = systemTranscriptions;
	}

	public Map<String, TranscriptionPointInfo> getSystemTranscriptions() {
		return systemTranscriptions;
	}

	public void setSystemTranscriptions(Map<String, TranscriptionPointInfo> systemTranscriptions) {
		this.systemTranscriptions = systemTranscriptions;
	}
	
	public TranscriptionPointInfo getTranscriptionSystemCode(String code) {
		return systemTranscriptions.get(code);
	}
	
}
