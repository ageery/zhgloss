package com.zixinxi.web.wicket.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zixinxi.AppConfig;
import com.zixinxi.domain.SerializableFunction;
import com.zixinxi.service.AppConfigService;

public class AppConfigModel<T> extends LoadableDetachableModel<T> {

	@SpringBean
	private AppConfigService service;
	
	private SerializableFunction<AppConfig, T> function;
	
	public AppConfigModel(SerializableFunction<AppConfig, T> function) {
		super();
		this.function = function;
		Injector.get().inject(this);
	}

	@Override
	protected T load() {
		return function.apply(service.getConfig());
	}
	
}
