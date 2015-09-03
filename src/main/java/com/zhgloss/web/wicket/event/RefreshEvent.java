package com.zhgloss.web.wicket.event;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.event.annotation.AbstractAjaxAwareEvent;

public class RefreshEvent extends AbstractAjaxAwareEvent {

	public RefreshEvent(AjaxRequestTarget target) {
		super(target);
	}

}
