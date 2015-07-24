package com.zhgloss.web.wicket.content.dictionary;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.service.TranscriptionService;

public class TranscriptionSystemInfoModel extends LoadableDetachableModel<TranscriptionSystemInfo> {

	@SpringBean
	private TranscriptionService service;
	
	private String code;
	
	public TranscriptionSystemInfoModel(String code) {
		super();
		this.code = code;
		Injector.get().inject(this);
	}
	
	@Override
	protected TranscriptionSystemInfo load() {
		return code == null ? null : service.getTranscriptionSystem(code).orElseThrow(RuntimeException::new);
	}

	@Override
	public void setObject(TranscriptionSystemInfo object) {
		super.setObject(object);
		code = object == null ? null : object.getCode();
	}

}
