package com.zixinxi.web.wicket.component;

import org.apache.wicket.model.IModel;

public class BootstrapExternalLink extends de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink {

	public BootstrapExternalLink(String id, IModel<String> hrefModel, IModel<?> labelModel) {
		super(id, hrefModel);
		setLabel(labelModel);
	}

}
