package com.zhgloss.web.wicket.event;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.event.annotation.AbstractPayloadTypedEvent;

public class DetailEvent<T> extends AbstractPayloadTypedEvent<T> {

	public DetailEvent(AjaxRequestTarget target, T payload) {
		super(target, payload);
	}

}
