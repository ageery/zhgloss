package com.zhgloss.web.wicket.content.about;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ResourceReference;

public class LinkedThumbnail extends Panel {

	public LinkedThumbnail(String id, IModel<String> linkModel, ResourceReference resourceReference) {
		super(id);
		queue(new ExternalLink("link", linkModel));
		queue(new Image("image", resourceReference));
	}

}
