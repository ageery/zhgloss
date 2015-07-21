package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.external.WordParts;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.SupplierModel;

public class WordPartsListPanel extends Panel {

	public WordPartsListPanel(String id, IModel<List<WordParts>> model) {
		super(id, model);
		add(new ListView<>("words",  model, item -> item.add(new WordPartsPanel("word", item.getModel(), new SupplierModel<>(() -> "#word_link_" + item.getIndex())))));
	}

}
