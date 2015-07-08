package com.zixinxi.repo;

import java.util.stream.Stream;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.SegmentDetail;
import com.zixinxi.domain.SortInfo;
import com.zixinxi.domain.WordDetail;
import com.zixinxi.domain.WordDetailSearchCriteria;
import com.zixinxi.domain.WordDetailSort;

public interface WordRepo {

	int count(WordDetailSearchCriteria criteria);
	
	Stream<WordDetail> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit);
	
	int updateWordsFromCedictData();
	
	Stream<SegmentDetail> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen);
	
}
