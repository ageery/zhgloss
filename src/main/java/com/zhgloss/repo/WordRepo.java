package com.zhgloss.repo;

import java.util.stream.Stream;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.SegmentDetail;
import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetail;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;

public interface WordRepo {

	int countAll();
	
	int count(WordDetailSearchCriteria criteria);
	
	Stream<WordDetail> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit);
	
	int updateWordsFromCedictData();
	
	Stream<SegmentDetail> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen);
	
}
