package com.zhgloss.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.domain.external.CedictLoadInfo;
import com.zhgloss.domain.external.SegmentedWord;
import com.zhgloss.domain.external.WordParts;

public interface WordService {

	int countAll();
	
	int loadNewCedictWords();
	
	Optional<LocalDateTime> getLastCedictLoad();
	
	Stream<CedictLoadInfo> getCedictLoadHistory(int offset, int limit);
	
	int count(WordDetailSearchCriteria criteria);
	
	Optional<WordParts> find(UUID id, String transcriptionSystem);
	
	Stream<WordParts> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit);
	
	Stream<SegmentedWord> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen);
	
}
