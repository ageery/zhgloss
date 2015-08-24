package com.zhgloss.repo;

import java.util.Optional;
import java.util.stream.Stream;

import org.jooq.DSLContext;

import com.zhgloss.domain.external.TranscriptionPlane;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.repo.jooq.tables.TranscriptionPoint;
import com.zhgloss.repo.jooq.tables.TranscriptionSystem;

public class TranscriptionRepoImpl extends AbstractRepoImpl implements TranscriptionRepo {

	public TranscriptionRepoImpl(DSLContext context) {
		super(context);
	}
	
	@Override
	public Stream<TranscriptionSystemInfo> getTranscriptionSystems() {
		return getContext()
				.select(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_SHORT_NAME)
				.from(TranscriptionSystem.TRANSCRIPTION_SYSTEM)
				.orderBy(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.fetch()
				.stream()
				.map(t -> new TranscriptionSystemInfo()
						.withCode(t.value1())
						.withName(t.value2())
						.withShortName(t.value3()));
	}

	@Override
	public Stream<TranscriptionPlane> getTranscriptionPlanes() {
		return getContext()
				.select(TranscriptionPoint.TRANSCRIPTION_POINT.PINYIN_SYLLABLE, TranscriptionPoint.TRANSCRIPTION_POINT.TONE, TranscriptionPoint.TRANSCRIPTION_POINT.TONED_REPRESENTATION)
				.from(TranscriptionPoint.TRANSCRIPTION_POINT)
				.orderBy(TranscriptionPoint.TRANSCRIPTION_POINT.PINYIN_SYLLABLE, TranscriptionPoint.TRANSCRIPTION_POINT.TONE)
				.fetch()
				.stream()
				.map(t -> new TranscriptionPlane()
							.withSyllableName(t.value1())
							.withTone(t.value2())
							.withTonedRepresentationMapper(c -> t.value3()));
	}

	@Override
	public Optional<TranscriptionSystemInfo> getTranscriptionSystem(String code) {
		return Optional.ofNullable(getContext()
				.select(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_SHORT_NAME)
				.from(TranscriptionSystem.TRANSCRIPTION_SYSTEM)
				.where(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE.eq(code))
				.orderBy(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.fetchOne())
			.flatMap(r -> Optional.of(new TranscriptionSystemInfo()
					.withCode(r.value1())
					.withName(r.value2())
					.withShortName(r.value3())));
	}
	
}
