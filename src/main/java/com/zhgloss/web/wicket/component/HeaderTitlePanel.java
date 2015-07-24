package com.zhgloss.web.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class HeaderTitlePanel extends Panel {

	public HeaderTitlePanel(String id, IModel<String> model) {
		super(id);
		add(new Label("title", model));
	}

}
