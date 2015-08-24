package com.zhgloss.web.wicket.content.home;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.component.WordPartsModal;
import com.zhgloss.web.wicket.content.dictionary.WordPartsModel;
import com.zhgloss.web.wicket.event.DetailEvent;
import com.zhgloss.web.wicket.model.SupplierModel;

public class ActivityPanel extends Panel {

	@SpringBean
	private WordService wordService;
	
	private IModel<TranscriptionSystemInfo> transcriptionSystemModel;
	private IModel<WordParts> wordPartsModel;
	
	private WordPartsModal modal;
	
	public ActivityPanel(String id, IModel<WordDetailSearchCriteria> criteriaModel, IModel<TranscriptionSystemInfo> transcriptionSystemModel, int maxResults) {
		super(id);
		this.transcriptionSystemModel = transcriptionSystemModel;
		setOutputMarkupId(true);
	
		wordPartsModel = new WordPartsModel(null, transcriptionSystemModel);
		modal = new WordPartsModal("modal", wordPartsModel, transcriptionSystemModel);
		add(modal);
		
		add(new ListView<>("words", 
				new SupplierModel<>(() -> 
					wordService.find(criteriaModel.getObject(), 
							transcriptionSystemModel.getObject().getCode(), 
							Stream.of(new SortInfo<>(WordDetailSort.CREATED, false), 
									new SortInfo<>(WordDetailSort.TRANSCRIPTION, true)), 
							0, 
							maxResults)
					.collect(Collectors.toList())), 
				item -> item.add(new WordSummaryPanel("word", item.getModel()))));
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		transcriptionSystemModel.detach();
		wordPartsModel.detach();
	}
	
	@OnEvent
	public void handleWordPartsDetailEvent(DetailEvent<WordParts> event) {
		wordPartsModel.setObject(event.getPayload());
		modal.show(event.getTarget());
	}
	
}
