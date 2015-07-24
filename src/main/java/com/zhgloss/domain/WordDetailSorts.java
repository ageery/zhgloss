package com.zhgloss.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum WordDetailSorts {

	TRADITIONAL(Arrays.asList(WordDetailSort.TRADITIONAL)),
	SIMPLIFIED(Arrays.asList(WordDetailSort.SIMPLIFIED)),
	TRANSCRIPTION(Arrays.asList(WordDetailSort.TRANSCRIPTION)),
	CREATED(Arrays.asList(WordDetailSort.CREATED));
	
	private List<WordDetailSort> sorts;
	
	WordDetailSorts(List<WordDetailSort> sorts) {
		this.sorts = sorts;
	}
	
	public Stream<WordDetailSort> getSorts() {
		return sorts.stream();
	}
	
	public static Stream<SortInfo<WordDetailSort>> getSortInfo(WordDetailSorts sorts, boolean ascending) {
		return sorts.getSorts().map(s -> new SortInfo<>(s, ascending));
	}
	
}
