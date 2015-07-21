package com.zixinxi.web.wicket.component;

import java.util.function.Function;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class HeaderPanelTitlePanel extends Panel {

	public HeaderPanelTitlePanel(String id, IModel<String> titleModel, Function<String, Component> panelFunction) {
		super(id);
		add(panelFunction.apply("panel"));
		add(new HeaderTitlePanel("title", titleModel));
	}

}
