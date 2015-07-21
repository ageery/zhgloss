package com.zixinxi.web.wicket.content.gloss;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.LambdaModel;

public class WordPartsPanel extends Panel {

	public WordPartsPanel(String id, IModel<WordParts> model, IModel<String> hrefModel) {
		super(id, model);
		add(new GlossWordLink("trad", hrefModel, new LambdaModel<>(model, new OpFunction<>(WordParts.FUNCTION_TRADITIONAL))));
		add(new Label("simp", new LambdaModel<>(model, new OpFunction<>(WordParts.FUNCTION_SIMPLIFIED))));
		add(new Label("transcription", new LambdaModel<>(model, new OpFunction<>(WordParts.FUNCTION_TRANSCRIPTION))));
		add(new ListView<>("defs", new LambdaModel<>(model, new OpFunction<>(WordParts.FUNCTION_DEFINITIONS)), 
				item -> item.add(new Label("def", item.getModel()))));
	}

}
