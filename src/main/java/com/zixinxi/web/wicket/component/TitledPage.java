package com.zixinxi.web.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.zixinxi.web.wicket.model.EmptyStringModel;

public class TitledPage extends BasePage {

	public TitledPage(PageParameters parameters) {
		super(parameters);
		add(new Label("header", getPageHeaderModel()));
		add(new Label("headerDescription", getDescriptionModel()));
	}
	
	protected IModel<String> getPageHeaderModel() {
		return getTitleModel();
	}
	
	protected IModel<String> getDescriptionModel() {
		return new ResourceModel("page.headerDescription", EmptyStringModel.get());
	}

}
