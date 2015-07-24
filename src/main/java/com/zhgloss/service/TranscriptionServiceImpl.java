package com.zhgloss.service;

import java.util.Optional;
import java.util.stream.Stream;

import com.zhgloss.domain.external.TranscriptionPlane;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.repo.TranscriptionRepo;

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
