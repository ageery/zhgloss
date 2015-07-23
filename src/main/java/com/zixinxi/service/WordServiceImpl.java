package com.zixinxi.service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.SortInfo;
import com.zixinxi.domain.WordDetail;
import com.zixinxi.domain.WordDetailSearchCriteria;
import com.zixinxi.domain.WordDetailSort;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.repo.CedictWordRepo;
import com.zixinxi.repo.WordRepo;

public class WordServiceImpl implements WordService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WordServiceImpl.class);
	
	private static final Function<WordDetail, WordParts> WORD_PARTS_MAPPER = wd -> new WordParts()
			.withId(wd.getId())
			.withTraditional(wd.getTraditionalCharacters())
			.withSimplified(wd.getSimplifiedCharacters())
			.withTranscription(wd.getTranscription())
			.withDefinitions(wd.getDefinitions());
	
	private WordRepo repo;
	private CedictWordRepo cedictWordRepo;

	public WordServiceImpl(WordRepo repo, CedictWordRepo cedictWordRepo) {
		super();
		this.repo = repo;
		this.cedictWordRepo = cedictWordRepo;
	}
	
	@Override
	public Stream<WordParts> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit) {
		return repo.find(criteria, transcriptionSystemCode, sorts, offset, limit)
				.map(WORD_PARTS_MAPPER);
	}

	@Override
	public int count(WordDetailSearchCriteria criteria) {
		return repo.count(criteria);
	}
	
	@Override
	public int loadNewCedictWords() {
		LOGGER.info("Downloading and loading new CEDICT data");
		cedictWordRepo.load();
		LOGGER.info("Adding new words from CEDICT data");
		int count = repo.updateWordsFromCedictData();
		return count;
	}
	
	@Override
	public Stream<SegmentedWord> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen) {
		return repo.segmentText(text, type, transcriptionSystemCode, maxLen)
				.map(w -> new SegmentedWord()
						.withText(w.getText())
						.withWords(w.getDetails().stream()
								.map(WORD_PARTS_MAPPER)
								.collect(Collectors.toList())));
	}

	@Override
	public Optional<WordParts> find(UUID id, String transcriptionSystem) {
		return find(new WordDetailSearchCriteria().withId(id), transcriptionSystem, Stream.of(new SortInfo<>(WordDetailSort.SIMPLIFIED)), 0, 1)
				.findFirst();
	}

	@Override
	public int countAll() {
		return repo.countAll();
	}
	
}
