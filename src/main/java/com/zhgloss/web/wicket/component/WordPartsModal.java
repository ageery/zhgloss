package com.zhgloss.web.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.model.LambdaModel;

public class WordPartsModal extends Modal<WordParts> {

	public WordPartsModal(String id, IModel<WordParts> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(id, model);
		
		/*
		 * Title.
		 */
		header(new ResourceModel("label.word_details"));
		
		/*
		 * Labels.
		 */
		add(new Label("traditionalLabel", new ResourceModel("label.traditional_characters")));
		add(new Label("simplifiedLabel", new ResourceModel("label.simplified_characters")));
		add(new Label("transcriptionLabel", new LambdaModel<>(transcriptionSystemModel, new OptionalFunction<>(TranscriptionSystemInfo.FUNCTION_NAME))));
		add(new Label("definitionsLabel", new ResourceModel("label.definitions")));
		
		/*
		 * Data.
		 */
		add(new Label("traditional", new LambdaModel<>(model, new OptionalFunction<>(WordParts.FUNCTION_TRADITIONAL))));
		add(new Label("simplified", new LambdaModel<>(model, new OptionalFunction<>(WordParts.FUNCTION_SIMPLIFIED))));
		add(new Label("transcription", new LambdaModel<>(model, new OptionalFunction<>(WordParts.FUNCTION_TRANSCRIPTION))));
		add(new ListView<>("definitions", new LambdaModel<>(model, new OptionalFunction<>(WordParts.FUNCTION_DEFINITIONS)), 
				item -> item.add(new Label("definition", item.getModel()))));
		
	}

}
