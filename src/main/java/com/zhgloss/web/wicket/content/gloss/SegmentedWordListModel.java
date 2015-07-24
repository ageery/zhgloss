package com.zhgloss.web.wicket.content.gloss;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.external.SegmentedWord;
import com.zhgloss.service.WordService;

public class SegmentedWordListModel extends LoadableDetachableModel<List<SegmentedWord>>{

	@SpringBean
	private WordService service;
	
	private IModel<SegmentedWordSearchCriteria> searchCriteriaModel;
	
	public SegmentedWordListModel(IModel<SegmentedWordSearchCriteria> searchCriteriaModel) {
		super();
		this.searchCriteriaModel = searchCriteriaModel;
		Injector.get().inject(this);
	}
	
	@Override
	protected List<SegmentedWord> load() {
		SegmentedWordSearchCriteria criteria = searchCriteriaModel.getObject();
		String text = criteria.getText();
		return StringUtils.isEmpty(text) ? Collections.emptyList() : 
			service.segmentText(text, criteria.getCharacterType(), 
					criteria.getTranscriptionSystem().getCode(), 
					criteria.getMaxWordLen())
				.collect(Collectors.toList());
	}

}
