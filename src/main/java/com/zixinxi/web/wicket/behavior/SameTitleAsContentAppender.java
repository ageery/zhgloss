package com.zixinxi.web.wicket.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

public class SameTitleAsContentAppender extends Behavior {

	public SameTitleAsContentAppender() {
		super();
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		tag.append("title", component.getDefaultModelObjectAsString(), "");
	}

}
