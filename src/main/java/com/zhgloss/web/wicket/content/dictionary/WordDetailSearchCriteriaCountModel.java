package com.zhgloss.web.wicket.content.dictionary;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.service.WordService;

public class WordDetailSearchCriteriaCountModel extends LoadableDetachableModel<Integer> {

	@SpringBean
	private WordService service;
	
	private IModel<WordDetailSearchCriteria> criteriaModel;
	
	public WordDetailSearchCriteriaCountModel(IModel<WordDetailSearchCriteria> criteriaModel) {
		this.criteriaModel = criteriaModel;
		Injector.get().inject(this);
	}
	
	@Override
	protected Integer load() {
		return service.count(criteriaModel.getObject());
	}

}
