package com.zhgloss.web.wicket.event;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.event.annotation.AbstractPayloadTypedEvent;

public class SearchEvent<T> extends AbstractPayloadTypedEvent<T> {

	public SearchEvent(AjaxRequestTarget target, T payload) {
		super(target, payload);
	}

}
