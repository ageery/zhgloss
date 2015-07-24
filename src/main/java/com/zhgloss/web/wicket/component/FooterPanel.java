package com.zhgloss.web.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.zhgloss.web.wicket.model.AppConfigModel;
import com.zhgloss.web.wicket.model.CurrentLocalDateTimeModel;

public class FooterPanel extends Panel {

	public FooterPanel(String id) {
		super(id);
		add(new Label("year", new CurrentLocalDateTimeModel("YYYY")));
		add(new Label("built", new AppConfigModel<>(config -> config.getBuildTimestamp())));
		add(new Label("version", new AppConfigModel<>(config -> config.getVersion())));
	}

}
