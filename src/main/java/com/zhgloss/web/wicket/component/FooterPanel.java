package com.zhgloss.web.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.model.AppConfigModel;
import com.zhgloss.web.wicket.model.CurrentLocalDateTimeModel;
import com.zhgloss.web.wicket.model.EmptyStringModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size;

public class FooterPanel extends Panel {

	public FooterPanel(String id) {
		super(id);
		add(new Label("year", new CurrentLocalDateTimeModel("YYYY")));
		add(new Label("built", new AppConfigModel<>(config -> config.getBuildTimestamp())));
		add(new Label("version", new AppConfigModel<>(config -> config.getVersion())));
		
		add(new BootstrapExternalLink("twitter", Model.of("http://twitter.com/zhgloss"), EmptyStringModel.get())
				.setIconType(Icons.ICON_TWITTER).setSize(Size.Large));
		add(new BootstrapExternalLink("email", Model.of("mailto:zhgloss@gmail.com"), EmptyStringModel.get())
				.setIconType(Icons.ICON_CONTACT).setSize(Size.Large));
	}

}
