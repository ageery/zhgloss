package com.zixinxi.web.wicket.component;

import java.util.function.Function;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class HeaderButtonTitlePanel extends Panel {

	public HeaderButtonTitlePanel(String id, IModel<String> titleModel, Function<String, Component> buttonFunction) {
		super(id);
		add(buttonFunction.apply("button"));
		add(new HeaderTitlePanel("title", titleModel));
	}

}
