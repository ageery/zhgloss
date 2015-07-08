package com.zixinxi.web.wicket.event;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.event.annotation.AbstractAjaxAwareEvent;

public class ClearEvent extends AbstractAjaxAwareEvent {

	public ClearEvent(AjaxRequestTarget target) {
		super(target);
	}

}
