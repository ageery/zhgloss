package com.zixinxi.web.wicket.content.dictionary;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.event.annotation.OnEvent;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.service.WordService;
import com.zixinxi.web.wicket.component.table.BootstrapDataTable;
import com.zixinxi.web.wicket.component.table.OrderedListFilteredColumn;
import com.zixinxi.web.wicket.component.table.TextFilteredColumn;
import com.zixinxi.web.wicket.event.SearchEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Alert;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Alert.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

public class DictionaryPanel extends GenericPanel<WordLookupCriteria> {
	
	@SpringBean
	private WordService service;
	
	private WordLookupDataSource dataSource;
	
	private DataTable<WordParts, WordSorts> table;
	private Component errorMessage;
	
	public DictionaryPanel(String id, IModel<WordLookupCriteria> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(id, model);
		
		add(new Label("instructions", new ResourceModel("instructions"))
			.setEscapeModelStrings(false));
		
		dataSource = new WordLookupDataSource(model, transcriptionSystemModel);
		FilterForm<WordLookupCriteria> form = new FilterForm<>("form", dataSource);
		add(form);
		
		errorMessage = new Alert("error", new ResourceModel("lookup.error"))
				.setCloseButtonVisible(true)
				.type(Type.Info)
				.setOutputMarkupPlaceholderTag(true)
				.setVisible(false);
		form.add(errorMessage);	
		
		table = new BootstrapDataTable<>("table", getColumns(transcriptionSystemModel), dataSource, 10);
		table.addTopToolbar(new FilterToolbar(table, form, dataSource));
		table.addBottomToolbar(new NoRecordsToolbar(table) {

			@Override
			public boolean isVisible() {
				return super.isVisible() && (!model.getObject().isEmpty());
			}
			
		});
		form.add(table); 
		
	}
	
	private List<IColumn<WordParts, WordSorts>> getColumns(IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		return asList(
				new TextFilteredColumn<>(
						new ResourceModel("column.traditional_characters"), 
						new OpFunction<>(WordParts.FUNCTION_TRADITIONAL), 
						WordLookupCriteria.PROPERTY_TRADITIONAL_CHARACTERS),
				new TextFilteredColumn<>(
						new ResourceModel("column.simplified_characters"), 
						new OpFunction<>(WordParts.FUNCTION_SIMPLIFIED), 
						WordLookupCriteria.PROPERTY_SIMPLIFIED_CHARACTERS),
				new TranscriptionColumn(getModel(), transcriptionSystemModel),
				new OrderedListFilteredColumn<>(
						new ResourceModel("column.definition"), 
						new OpFunction<>(WordParts.FUNCTION_DEFINITIONS), 
						WordLookupCriteria.PROPERTY_DEFINITION)
		);
	}
	
	@OnEvent(types = WordLookupCriteria.class, stop = true)
	public void handleSearchEvent(SearchEvent<WordLookupCriteria> event) {
		if (event.getPayload().isEmpty()) {
			errorMessage.setVisible(true);
			event.getTarget().add(errorMessage);
		} else {
			errorMessage.setVisible(false);
			dataSource.criteriaChanged();
			event.getTarget().add(table, errorMessage);
		}
	}
	
}
