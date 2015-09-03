package com.zhgloss.web.wicket.content.dictionary;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.SerializableBiConsumer;
import com.zhgloss.domain.SerializableFunction;
import com.zhgloss.domain.SerializableProperty;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.component.form.ChoiceRenderer;
import com.zhgloss.web.wicket.component.table.TextFilteredColumn;
import com.zhgloss.web.wicket.event.RefreshEvent;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

// FIXME: when the char type changes, null out the char type value
public class WordColumn extends TextFilteredColumn<WordParts, WordSorts, String, WordLookupCriteria, String> {

	private IModel<WordLookupCriteria> model;
	private IModel<CharacterType> characterTypeModel;
	
	public WordColumn(IModel<WordLookupCriteria> model, IModel<CharacterType> characterTypeModel) {
		super(new ResourceModel("column.transcription"), 
				new Y(characterTypeModel),
				new X(characterTypeModel));
		this.model = model;
		this.characterTypeModel = characterTypeModel;
	}
	
	private static class Y implements SerializableFunction<WordParts, String> {

		private IModel<CharacterType> characterTypeModel;
		
		public Y(IModel<CharacterType> characterTypeModel) {
			this.characterTypeModel = characterTypeModel;
		}
		
		@Override
		public String apply(WordParts t) {
			return CharacterType.SIMPLFIED.equals(characterTypeModel.getObject()) ?  t.getSimplified() : t.getTraditional();
		}
		
	}

	private static class X implements SerializableProperty<WordLookupCriteria, String> {

		private IModel<CharacterType> characterTypeModel;
		
		public X(IModel<CharacterType> characterTypeModel) {
			this.characterTypeModel = characterTypeModel;
		}
		
		@Override
		public SerializableFunction<WordLookupCriteria, String> getGetter() {
			return c -> CharacterType.SIMPLFIED.equals(characterTypeModel.getObject()) ? c.getSimplifiedCharacters() : c.getTraditionalCharacters();
		}

		@Override
		public SerializableBiConsumer<WordLookupCriteria, String> getSetter() {
			return (c, v) -> {
				if (CharacterType.SIMPLFIED.equals(characterTypeModel.getObject())) {
					c.setSimplifiedCharacters(v);
					c.setTraditionalCharacters(null);
				} else {
					c.setTraditionalCharacters(v);
					c.setSimplifiedCharacters(null);
				}
			};
		}
	}
	
	@Override
	public String getCssClass() {
		return "h4";
	}

	@Override
	public Component getHeader(String componentId) {
		return new SelectPanel(componentId, model, characterTypeModel);
	}
	
	private static class SelectPanel extends Panel {

		public SelectPanel(String id, IModel<WordLookupCriteria> model, IModel<CharacterType> characterTypeModel) {
			super(id, model);
			
			FormComponent<CharacterType> characterTypeChoice = 
				new BootstrapSelect<CharacterType>("select",
							characterTypeModel,
							new SupplierModel<>(() -> Arrays.asList(CharacterType.values())),
							new ChoiceRenderer<>(CharacterType::getDisplayValue, (ct, index) -> ct.getDbValue()))
						.setLabel(new ResourceModel("label.character_type"))
						.setRequired(true);
			add(characterTypeChoice);
			characterTypeChoice.add(new AjaxFormComponentUpdatingBehavior("change") {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					characterTypeChoice.send(characterTypeChoice, Broadcast.BUBBLE, new RefreshEvent(target));
				}
				
			});	
		}
		
	}

}
