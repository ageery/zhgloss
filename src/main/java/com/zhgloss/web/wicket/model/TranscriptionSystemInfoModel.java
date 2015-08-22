package com.zhgloss.web.wicket.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.service.TranscriptionService;

public class TranscriptionSystemInfoModel extends LoadableDetachableDependentModel<TranscriptionSystemInfo, String>{

	@SpringBean
	private TranscriptionService service;
	
	public TranscriptionSystemInfoModel(IModel<String> dependentModel) {
		super(dependentModel);
		Injector.get().inject(this);
	}

	@Override
	protected TranscriptionSystemInfo load() {
		return service.getTranscriptionSystem(getDependentModel().getObject()).orElse(null);
	}

}
