package com.zhgloss.web.wicket.content.dictionary;

import java.util.Arrays;
import java.util.List;

import com.zhgloss.domain.WordDetailSort;

public enum WordSorts {

	TRADITIONAL(Arrays.asList(WordDetailSort.TRADITIONAL)),
	SIMPLIFIED(Arrays.asList(WordDetailSort.SIMPLIFIED)),
	TRANSCRIPTION(Arrays.asList(WordDetailSort.TRANSCRIPTION));
	
	private List<WordDetailSort> sorts;
	
	WordSorts(List<WordDetailSort> sorts) {
		this.sorts = sorts;
	}

	public List<WordDetailSort> getSorts() {
		return sorts;
	}
	
}
