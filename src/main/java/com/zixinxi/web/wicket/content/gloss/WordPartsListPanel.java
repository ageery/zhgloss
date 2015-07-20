package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.external.WordParts;
import com.zixinxi.web.wicket.component.ListView;

public class WordPartsListPanel extends Panel {

	public WordPartsListPanel(String id, IModel<List<WordParts>> model) {
		super(id, model);
		add(new ListView<>("words",  model, item -> item.add(new WordPartsPanel("word", item.getModel()))));
	}

}
