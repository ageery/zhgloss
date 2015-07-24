package com.zhgloss.web.wicket.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class EmptyStringModel extends AbstractReadOnlyModel<String> {

	private static final IModel<String> INSTANCE = new EmptyStringModel();
	
	private EmptyStringModel() {
		super();
	}
	
	@Override
	public String getObject() {
		return "";
	}

	public static IModel<String> get() {
		return INSTANCE;
	}
	
}
