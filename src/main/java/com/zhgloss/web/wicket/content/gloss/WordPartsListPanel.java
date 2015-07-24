package com.zhgloss.web.wicket.content.gloss;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.OpFunction;
import com.zhgloss.domain.external.SegmentedWord;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.model.LambdaModel;

public class WordPartsListPanel extends Panel {

	public WordPartsListPanel(String id, IModel<SegmentedWord> model, IModel<String> wordLinkModel) {
		super(id, model);
		add(new ListView<>("words", new LambdaModel<>(model, new OpFunction<>(SegmentedWord.FUNCTION_WORDS)), 
				item -> item.add(new WordPartsPanel("word", new LambdaModel<>(model, new OpFunction<>(SegmentedWord.FUNCTION_TEXT)), item.getModel(), wordLinkModel))));
	}

}
