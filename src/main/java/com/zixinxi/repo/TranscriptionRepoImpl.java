package com.zixinxi.repo;

import java.util.Optional;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.Record2;

import com.zixinxi.domain.external.TranscriptionPlane;
import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.repo.jooq.tables.TranscriptionPoint;
import com.zixinxi.repo.jooq.tables.TranscriptionSystem;

public class TranscriptionRepoImpl extends AbstractRepoImpl implements TranscriptionRepo {

	public TranscriptionRepoImpl(DSLContext context) {
		super(context);
	}
	
	@Override
	public Stream<TranscriptionSystemInfo> getTranscriptionSystems() {
		return getContext()
				.select(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.from(TranscriptionSystem.TRANSCRIPTION_SYSTEM)
				.orderBy(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.fetch()
				.stream()
				.map(t -> new TranscriptionSystemInfo()
						.withCode(t.value1())
						.withName(t.value2()));
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
		Record2<String, String> t = getContext()
				.select(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE, TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.from(TranscriptionSystem.TRANSCRIPTION_SYSTEM)
				.where(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_CODE.eq(code))
				.orderBy(TranscriptionSystem.TRANSCRIPTION_SYSTEM.TS_NAME)
				.fetchOne();
		return t == null ? Optional.empty() : Optional.of(new TranscriptionSystemInfo()
				.withCode(t.value1())
				.withName(t.value2()));
	}
	
}
