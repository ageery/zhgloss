package com.zixinxi.web.wicket.content.gloss;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.LambdaModel;

public class WordPartsListPanel extends Panel {

	public WordPartsListPanel(String id, IModel<SegmentedWord> model, IModel<String> wordLinkModel) {
		super(id, model);
		add(new ListView<>("words", new LambdaModel<>(model, new OpFunction<>(SegmentedWord.FUNCTION_WORDS)), 
				item -> item.add(new WordPartsPanel("word", new LambdaModel<>(model, new OpFunction<>(SegmentedWord.FUNCTION_TEXT)), item.getModel(), wordLinkModel))));
	}

}
