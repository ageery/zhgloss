package com.zixinxi.service;

import java.util.Optional;
import java.util.stream.Stream;

import com.zixinxi.domain.external.TranscriptionPlane;
import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.repo.TranscriptionRepo;

public class TranscriptionServiceImpl implements TranscriptionService {

	private TranscriptionRepo repo;
	
	public TranscriptionServiceImpl(TranscriptionRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Stream<TranscriptionSystemInfo> getTranscriptionSystems() {
		return repo.getTranscriptionSystems();
	}

	@Override
	public Stream<TranscriptionPlane> getTranscriptionPlanes() {
		return repo.getTranscriptionPlanes();
	}

	@Override
	public Optional<TranscriptionSystemInfo> getTranscriptionSystem(String code) {
		return repo.getTranscriptionSystem(code);
	}
	
}
