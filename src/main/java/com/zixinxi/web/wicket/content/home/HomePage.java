package com.zixinxi.web.wicket.content.home;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.web.wicket.component.BasePage;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

@MountPath("/")
public class HomePage extends BasePage {

	public HomePage(PageParameters parameters) {
		super(parameters);
		add(new Heading("header", "Home Page Header"));
	}

}
