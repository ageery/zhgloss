package com.zhgloss.web.wicket.component;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.web.wicket.component.form.ChoiceRenderer;
import com.zhgloss.web.wicket.content.dictionary.TranscriptionSystemInfoListModel;
import com.zhgloss.web.wicket.model.LambdaModel;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

public class UserSettingsFormPanel extends Panel {

	public UserSettingsFormPanel(String id, IModel<UserSettings> model) {
		super(id, model);

		Form<UserSettings> form = new Form<>("form", model);

		/*
		 * Character type.
		 */
		form.add(new FormGroup("typeGroup").add(new BootstrapSelect<>("types",
				new LambdaModel<>(model, 
						UserSettings::getCharacterType,
						UserSettings::setCharacterType),
				new SupplierModel<>(() -> Arrays.asList(CharacterType.values())),
				new ChoiceRenderer<>(CharacterType::getDisplayValue, (ct, index) -> ct.getDbValue()))
						.setLabel(new ResourceModel("label.character_type"))
						.setRequired(true)));

		/*
		 * Transcription system.
		 */
		form.add(new FormGroup("systemGroup").add(new BootstrapSelect<>("systems",
				new LambdaModel<>(model, 
						UserSettings::getTranscriptionSystem,
						UserSettings::setTranscriptionSystem),
				new TranscriptionSystemInfoListModel(),
				new ChoiceRenderer<>(ts -> ts.getName(), (ts, index) -> ts.getCode()))
						.setLabel(new ResourceModel("label.transcription_system"))
						.setRequired(true)));

	}

}
