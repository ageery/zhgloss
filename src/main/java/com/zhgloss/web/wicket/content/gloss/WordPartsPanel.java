package com.zhgloss.web.wicket.content.gloss;

import java.util.stream.Collectors;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.OpFunction;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.model.LambdaModel;

public class WordPartsPanel extends Panel {

	public WordPartsPanel(String id, IModel<String> textModel, IModel<WordParts> partsModel, IModel<String> hrefModel) {
		super(id, textModel);
		add(new ExternalLink("text", hrefModel, textModel));
		add(new Label("transcription", new LambdaModel<>(partsModel, new OpFunction<>(WordParts.FUNCTION_TRANSCRIPTION))));
		add(new Label("defs", new LambdaModel<>(partsModel, wp -> wp.getDefinitionStream().collect(Collectors.joining("; ")))));
	}

}
