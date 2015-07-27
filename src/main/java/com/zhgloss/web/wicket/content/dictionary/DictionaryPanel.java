package com.zhgloss.web.wicket.content.dictionary;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.component.table.BootstrapDataTable;
import com.zhgloss.web.wicket.component.table.OrderedListFilteredColumn;
import com.zhgloss.web.wicket.component.table.TextFilteredColumn;
import com.zhgloss.web.wicket.event.SearchEvent;

public class DictionaryPanel extends GenericPanel<WordLookupCriteria> {
	
	@SpringBean
	private WordService service;
	
	private WordLookupDataSource dataSource;
	private DataTable<WordParts, WordSorts> table;
	
	public DictionaryPanel(String id, IModel<WordLookupCriteria> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(id, model);
		
		dataSource = new WordLookupDataSource(model, transcriptionSystemModel);
		FilterForm<WordLookupCriteria> form = new FilterForm<>("form", dataSource);
		add(form);
		
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
						new OptionalFunction<>(WordParts.FUNCTION_TRADITIONAL), 
						WordLookupCriteria.PROPERTY_TRADITIONAL_CHARACTERS),
				new TextFilteredColumn<>(
						new ResourceModel("column.simplified_characters"), 
						new OptionalFunction<>(WordParts.FUNCTION_SIMPLIFIED), 
						WordLookupCriteria.PROPERTY_SIMPLIFIED_CHARACTERS),
				new TranscriptionColumn(getModel(), transcriptionSystemModel),
				new OrderedListFilteredColumn<>(
						new ResourceModel("column.definition"), 
						new OptionalFunction<>(WordParts.FUNCTION_DEFINITIONS), 
						WordLookupCriteria.PROPERTY_DEFINITION)
		);
	}
	
	@OnEvent(types = WordLookupCriteria.class, stop = true)
	public void handleSearchEvent(SearchEvent<WordLookupCriteria> event) {
		if (!event.getPayload().isEmpty()) {
			dataSource.criteriaChanged();
			event.getTarget().add(table);
		}
	}
	
}
