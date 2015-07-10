package com.zixinxi.web.wicket.content.segment;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public class SegmentPagePanel extends GenericPanel<String> {

	public SegmentPagePanel(String id, IModel<String> model) {
		super(id, model);
	}

}
