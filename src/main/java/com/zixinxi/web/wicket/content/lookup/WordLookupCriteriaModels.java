package com.zixinxi.web.wicket.content.lookup;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.wicketstuff.lazymodel.LazyModel;

public final class WordLookupCriteriaModels {

	public static final LazyModel<String> LM_TRADITIONAL_CHARACTERS = model(from(WordLookupCriteria.class).getTraditionalCharacters());
	public static final LazyModel<String> LM_SIMPLIFIED_CHARACTERS = model(from(WordLookupCriteria.class).getSimplifiedCharacters());
	public static final LazyModel<String> LM_PINYIN = model(from(WordLookupCriteria.class).getPinyin());
	public static final LazyModel<String> LM_DEFINITION = model(from(WordLookupCriteria.class).getDefinition());
	
	private WordLookupCriteriaModels() {
		super();
	}
	
}
