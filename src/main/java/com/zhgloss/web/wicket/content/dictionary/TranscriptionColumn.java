package com.zhgloss.web.wicket.content.dictionary;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.OpFunction;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.component.form.ChoiceRenderer;
import com.zhgloss.web.wicket.component.table.TextFilteredColumn;
import com.zhgloss.web.wicket.event.SearchEvent;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

public class TranscriptionColumn extends TextFilteredColumn<WordParts, WordSorts, String, WordLookupCriteria, String> {

	private IModel<WordLookupCriteria> model;
	private IModel<TranscriptionSystemInfo> transcriptionSystemModel;
	
	public TranscriptionColumn(IModel<WordLookupCriteria> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(new ResourceModel("column.transcription"), 
				new OpFunction<>(WordParts.FUNCTION_TRANSCRIPTION),
				WordLookupCriteria.PROPERTY_PINYIN);
		this.model = model;
		this.transcriptionSystemModel = transcriptionSystemModel;
	}

	@Override
	public Component getHeader(String componentId) {
		return new SelectPanel(componentId, model, transcriptionSystemModel);
	}
	
	private static class SelectPanel extends Panel {

		public SelectPanel(String id, IModel<WordLookupCriteria> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
			super(id, model);
			
			FormComponent<TranscriptionSystemInfo> systemChoice = new BootstrapSelect<>("select", 
					transcriptionSystemModel, 
					new TranscriptionSystemInfoListModel(),
					new ChoiceRenderer<>(ts -> ts.getName(), (ts, index) -> ts.getCode()));
			add(systemChoice);
			systemChoice.add(new AjaxFormComponentUpdatingBehavior("change") {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					WordLookupCriteria criteria = model.getObject();
					if (!criteria.isEmpty()) {
						systemChoice.send(systemChoice, Broadcast.BUBBLE, new SearchEvent<>(target, criteria));
					}
				}
				
			});	
		}
		
	}

}
