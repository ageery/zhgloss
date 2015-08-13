package com.zhgloss.web.wicket.content.home;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.model.SupplierModel;

public class ActivityPanel extends Panel {

	@SpringBean
	private WordService wordService;
	
	public ActivityPanel(String id, IModel<WordDetailSearchCriteria> criteriaModel, IModel<String> transcriptionSystemModel, int maxResults) {
		super(id);
		add(new ListView<>("words", 
				new SupplierModel<>(() -> 
					wordService.find(criteriaModel.getObject(), 
							transcriptionSystemModel.getObject(), 
							Stream.of(new SortInfo<>(WordDetailSort.CREATED, false), 
									new SortInfo<>(WordDetailSort.TRANSCRIPTION, true)), 
							0, 
							maxResults)
					.collect(Collectors.toList())), 
				item -> item.add(new WordSummaryPanel("word", item.getModel()))));
	}

}
