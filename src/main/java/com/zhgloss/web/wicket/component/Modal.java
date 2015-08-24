package com.zhgloss.web.wicket.component;

import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.web.wicket.event.CancelEvent;

public class Modal<T> extends de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal<T> {

	public Modal(String id, IModel<T> model) {
		super(id, model);
	}
	
	@Override
	public de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal<T> show(IPartialPageRequestHandler target) {
		target.add(this);
		return super.show(target);
	}

	@OnEvent(stop = true)
	public void handleCancelEvent(CancelEvent event) {
		close(event.getTarget());
	}
	
}
