package com.zixinxi.web.wicket.content.segment;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.model.IModel;
import org.wicketstuff.lazymodel.LazyModel;

import com.zixinxi.domain.external.SegmentedWord;

public final class SegmentedWordModels {

	private static final LazyModel<String> LM_TEXT = model(from(SegmentedWord.class).getText());
	
	private SegmentedWordModels() {
		super();
	}
	
	public static IModel<String> bindText(IModel<SegmentedWord> model) {
		return LM_TEXT.bind(model);
	}
	
}
