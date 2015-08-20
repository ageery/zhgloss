package com.zhgloss.web.wicket.content.home;

import java.util.stream.Collectors;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.model.LambdaModel;

public class WordSummaryPanel extends Panel {

	public WordSummaryPanel(String id, IModel<WordParts> partsModel) {
		super(id, partsModel); 
		add(new Label("text", new LambdaModel<>(partsModel, 
				new OptionalFunction<>(CharacterType.SIMPLFIED.equals(ZhGlossSession.get().getUserSettings().getCharacterType()) ? 
						WordParts.FUNCTION_SIMPLIFIED : 
						WordParts.FUNCTION_TRADITIONAL))));
		add(new Label("transcription", new LambdaModel<>(partsModel, new OptionalFunction<>(WordParts.FUNCTION_TRANSCRIPTION))));
		add(new Label("defs", new LambdaModel<>(partsModel, wp -> wp.getDefinitionStream().collect(Collectors.joining("; ")))));
	}

}
