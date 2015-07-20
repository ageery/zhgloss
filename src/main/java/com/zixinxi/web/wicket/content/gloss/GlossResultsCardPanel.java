package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.ListView;

public class GlossResultsCardPanel extends Panel {

	public GlossResultsCardPanel(String id, IModel<List<SegmentedWord>> model) {
		super(id, model);
		add(new ListView<>("words", model, 
				item -> item.add(new WordPanel("word", item.getModel()))));
	}

}
