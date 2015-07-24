package com.zhgloss.web.wicket.content.dictionary;

import java.util.UUID;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.service.WordService;

public class WordPartsModel extends LoadableDetachableModel<WordParts> {

	@SpringBean
	private WordService service;
	
	private IModel<TranscriptionSystemInfo> transcriptionSystemModel;
	private UUID id;
	
	public WordPartsModel(WordParts wordParts, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		super(wordParts);
		this.id = wordParts.getId();
		this.transcriptionSystemModel = transcriptionSystemModel;
		Injector.get().inject(this);
	}
	
	@Override
	protected WordParts load() {
		return service.find(id, transcriptionSystemModel.getObject().getCode()).orElse(null);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		transcriptionSystemModel.detach();
	}

}
