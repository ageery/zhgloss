package com.zixinxi.web.wicket.content.lookup;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import java.util.List;

import org.wicketstuff.lazymodel.LazyModel;

import com.zixinxi.domain.external.WordParts;

public final class WordPartsModels {

	public static final LazyModel<String> LM_TRADITIONAL_CHARACTERS = model(from(WordParts.class).getTraditional());
	public static final LazyModel<String> LM_SIMPLIFIED_CHARACTERS = model(from(WordParts.class).getSimplified());
	public static final LazyModel<String> LM_TRANSCRIPTION = model(from(WordParts.class).getTranscription());
	public static final LazyModel<List<String>> LM_DEFINITIONS = model(from(WordParts.class).getDefinitions());
	
	private WordPartsModels() {
		super();
	}
	
}
