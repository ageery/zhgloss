package com.zixinxi.repo;

import java.util.Optional;
import java.util.stream.Stream;

import com.zixinxi.domain.external.TranscriptionPlane;
import com.zixinxi.domain.external.TranscriptionSystemInfo;

public interface TranscriptionRepo {

	Optional<TranscriptionSystemInfo> getTranscriptionSystem(String code);
	
	Stream<TranscriptionSystemInfo> getTranscriptionSystems(); 
	
	Stream<TranscriptionPlane> getTranscriptionPlanes();
	
}
