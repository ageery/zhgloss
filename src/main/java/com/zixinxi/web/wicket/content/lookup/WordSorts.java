package com.zixinxi.web.wicket.content.lookup;

import java.util.Arrays;
import java.util.List;

import com.zixinxi.domain.WordDetailSort;

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
