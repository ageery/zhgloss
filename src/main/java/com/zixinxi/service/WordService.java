package com.zixinxi.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.SortInfo;
import com.zixinxi.domain.WordDetailSearchCriteria;
import com.zixinxi.domain.WordDetailSort;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.domain.external.WordParts;

public interface WordService {

	int countAll();
	
	int loadNewCedictWords();
	
	Optional<LocalDateTime> getLastCedictLoad();
	
	int count(WordDetailSearchCriteria criteria);
	
	Optional<WordParts> find(UUID id, String transcriptionSystem);
	
	Stream<WordParts> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit);
	
	Stream<SegmentedWord> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen);
	
}
