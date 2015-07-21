package com.zixinxi.web.wicket.content.gloss;

import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;

public class GlossWordLink extends BootstrapExternalLink {

	public GlossWordLink(String id, IModel<String> hrefModel, IModel<?> labelModel) {
		super(id, hrefModel);
		setLabel(labelModel);
	}

}
