package com.zixinxi.web.wicket.content.dictionary;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.service.TranscriptionService;

public class TranscriptionSystemInfoListModel extends LoadableDetachableModel<List<TranscriptionSystemInfo>> {

	@SpringBean
	private TranscriptionService service;
	
	public TranscriptionSystemInfoListModel() {
		super();
		Injector.get().inject(this);
	}
	
	@Override
	protected List<TranscriptionSystemInfo> load() {
		return service.getTranscriptionSystems().collect(Collectors.toList());
	}

}
