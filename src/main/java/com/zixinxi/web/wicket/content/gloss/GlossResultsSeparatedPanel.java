package com.zixinxi.web.wicket.content.gloss;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.LambdaModel;
import com.zixinxi.web.wicket.model.SupplierModel;

public class GlossResultsSeparatedPanel extends Panel {

	public GlossResultsSeparatedPanel(String id, IModel<List<SegmentedWord>> model) {
		super(id, model);
		add(new ListView<>("text", model,
				item -> item.add(new Label("word", new LambdaModel<>(item.getModel(), new OpFunction<>(SegmentedWord.FUNCTION_TEXT))))));
		add(new ListView<>("defs", 
				new SupplierModel<>(() -> model.getObject().stream().filter(SegmentedWord::hasDefinition).collect(Collectors.toList())), 
				item -> item.add(new WordPartsListPanel("def", 
						new LambdaModel<>(item.getModel(), new OpFunction<>(SegmentedWord.FUNCTION_WORDS))))));
	}

}
