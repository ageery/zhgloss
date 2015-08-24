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
import com.zhgloss.web.wicket.component.WordPartsModal;
import com.zhgloss.web.wicket.component.link.DetailLink;
import com.zhgloss.web.wicket.component.table.BootstrapDataTable;
import com.zhgloss.web.wicket.component.table.LinkFilteredColumn;
import com.zhgloss.web.wicket.component.table.OrderedListFilteredColumn;
import com.zhgloss.web.wicket.event.DetailEvent;
import com.zhgloss.web.wicket.event.SearchEvent;
import com.zhgloss.web.wicket.model.LambdaModel;

public class DictionaryPanel extends GenericPanel<WordLookupCriteria> {
	
	@SpringBean
	private WordService service;
	
	private WordPartsModel wordPartsModel;
	
	private WordLookupDataSource dataSource;
	private DataTable<WordParts, WordSorts> table;
	private WordPartsModal modal;
	
	public DictionaryPanel(String id, IModel<WordLookupCriteria> model, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(id, model);
		
		wordPartsModel = new WordPartsModel(null, transcriptionSystemModel);
		modal = new WordPartsModal("modal", wordPartsModel, transcriptionSystemModel);
		add(modal);
		
		dataSource = new WordLookupDataSource(model, transcriptionSystemModel);
		FilterForm<WordLookupCriteria> form = new FilterForm<>("form", dataSource);
		add(form);
		
		table = new BootstrapDataTable<>("table", getColumns(transcriptionSystemModel), dataSource, 10);
		table.addTopToolbar(new FilterToolbar(table, form));
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
				new LinkFilteredColumn<>(
						new ResourceModel("column.traditional_characters"), 
						WordLookupCriteria.PROPERTY_TRADITIONAL_CHARACTERS,
						(id, rowModel) -> new DetailLink<>(id, rowModel, new LambdaModel<>(rowModel, new OptionalFunction<>(WordParts.FUNCTION_TRADITIONAL)))),
				new LinkFilteredColumn<>(
						new ResourceModel("column.simplified_characters"),  
						WordLookupCriteria.PROPERTY_SIMPLIFIED_CHARACTERS,
						(id, rowModel) -> new DetailLink<>(id, rowModel, new LambdaModel<>(rowModel, new OptionalFunction<>(WordParts.FUNCTION_SIMPLIFIED)))),
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
	
	@OnEvent(types = WordParts.class)
	public void handleDetailEvent(DetailEvent<WordParts> event) {
		wordPartsModel.setObject(event.getPayload());
		modal.show(event.getTarget());
	}
	
}
