package com.zixinxi.web.wicket.content.segment;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.web.wicket.component.button.SearchButton;
import com.zixinxi.web.wicket.component.form.ChoiceRenderer;
import com.zixinxi.web.wicket.content.dictionary.CharacterTypeListModel;
import com.zixinxi.web.wicket.content.dictionary.TranscriptionSystemInfoListModel;
import com.zixinxi.web.wicket.model.LambdaModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

public class SegmentFormPanel extends Panel {

	public SegmentFormPanel(String id, IModel<SegmentedWordSearchCriteria> model) {
		super(id, model);
		
		/*
		 * Form.
		 */
		Form<SegmentedWordSearchCriteria> form = new BootstrapForm<>("form", model);
		add(form);

		/*
		 * Character type.
		 */
		form.add(new FormGroup("typeGroup")
			.add(new BootstrapSelect<>("types", 
						new LambdaModel<>(model, SegmentedWordSearchCriteria::getCharacterType, SegmentedWordSearchCriteria::setCharacterType), 
						new CharacterTypeListModel(),
						new ChoiceRenderer<>(CharacterType::getDisplayValue, (ct, index) -> ct.getDbValue()))
					.setLabel(new ResourceModel("label.character_type"))
					.setRequired(true)));
		
		/*
		 * Transcription system. 
		 */
		form.add(new FormGroup("systemGroup")
			.add(new BootstrapSelect<>("systems", 
						new LambdaModel<>(model, SegmentedWordSearchCriteria::getTranscriptionSystem, SegmentedWordSearchCriteria::setTranscriptionSystem), 
						new TranscriptionSystemInfoListModel(),
						new ChoiceRenderer<>(ts -> ts.getName(), (ts, index) -> ts.getCode()))
					.setLabel(new ResourceModel("label.transcription_system"))
					.setRequired(true)));
		
		/*
		 * Text.
		 */
		form.add(new FormGroup("textGroup")
			.add(new TextArea<>("text", 
						new LambdaModel<>(model, SegmentedWordSearchCriteria::getText, SegmentedWordSearchCriteria::setText))
					.setRequired(true)
					.add(new InputBehavior())));
		
		/*
		 * Button.
		 */
		form.add(new SearchButton("segmentButton", new ResourceModel("label.gloss"), Type.Primary));
		
	}

}
