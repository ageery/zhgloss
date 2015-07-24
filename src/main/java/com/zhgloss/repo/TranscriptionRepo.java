package com.zhgloss.repo;

import java.util.Optional;
import java.util.stream.Stream;

import com.zhgloss.domain.external.TranscriptionPlane;
import com.zhgloss.domain.external.TranscriptionSystemInfo;

public interface TranscriptionRepo {

	Optional<TranscriptionSystemInfo> getTranscriptionSystem(String code);
	
	Stream<TranscriptionSystemInfo> getTranscriptionSystems(); 
	
	Stream<TranscriptionPlane> getTranscriptionPlanes();
	
}
