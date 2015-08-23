package com.zhgloss.web.wicket.component;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.web.wicket.component.button.CancelButton;
import com.zhgloss.web.wicket.component.button.SaveButton;
import com.zhgloss.web.wicket.component.form.ChoiceRenderer;
import com.zhgloss.web.wicket.content.dictionary.TranscriptionSystemInfoListModel;
import com.zhgloss.web.wicket.model.LambdaModel;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

public class UserSettingsModal extends Modal<UserSettings> {

	public UserSettingsModal(String id, IModel<UserSettings> model) {
		super(id, model);
		
		/*
		 * Title.
		 */
		header(new ResourceModel("label.user_settings"));
		
		/*
		 * Form.
		 */
		Form<UserSettings> form = new BootstrapForm<>("form", model);
		add(form);

		/*
		 * Character type.
		 */
		form.add(new FormGroup("typeGroup")
				.add(new BootstrapSelect<>("types",
					new LambdaModel<>(model, 
							UserSettings::getCharacterType,
							UserSettings::setCharacterType),
					new SupplierModel<>(() -> Arrays.asList(CharacterType.values())),
					new ChoiceRenderer<>(CharacterType::getDisplayValue, (ct, index) -> ct.getDbValue()))
				.setLabel(new ResourceModel("label.character_type"))
				.setRequired(true)
				.add(new FormBehavior())));

		/*
		 * Transcription system.
		 */
		form.add(new FormGroup("systemGroup")
				.add(new BootstrapSelect<>("systems",
					new LambdaModel<>(model, 
							UserSettings::getTranscriptionSystem,
							UserSettings::setTranscriptionSystem),
					new TranscriptionSystemInfoListModel(),
					new ChoiceRenderer<>(ts -> ts.getName(), (ts, index) -> ts.getCode()))
				.setLabel(new ResourceModel("label.transcription_system"))
				.setRequired(true)
				.add(new FormBehavior())));
		
		/*
		 * Buttons.
		 */
		addButton(new CancelButton(Modal.BUTTON_MARKUP_ID, form));
		addButton(new SaveButton(Modal.BUTTON_MARKUP_ID, form));
		
	}
	
}
