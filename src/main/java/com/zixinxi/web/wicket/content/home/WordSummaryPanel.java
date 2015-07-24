package com.zixinxi.web.wicket.content.home;

import java.util.stream.Collectors;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.web.wicket.model.LambdaModel;

public class WordSummaryPanel extends Panel {

	public WordSummaryPanel(String id, IModel<WordParts> partsModel) {
		super(id, partsModel);
		add(new Label("text", new LambdaModel<>(partsModel, new OpFunction<>(WordParts.FUNCTION_SIMPLIFIED))));
		add(new Label("transcription", new LambdaModel<>(partsModel, new OpFunction<>(WordParts.FUNCTION_TRANSCRIPTION))));
		add(new Label("defs", new LambdaModel<>(partsModel, wp -> wp.getDefinitionStream().collect(Collectors.joining("; ")))));
	}

}
