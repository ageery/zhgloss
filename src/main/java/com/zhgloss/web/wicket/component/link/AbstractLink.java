package com.zhgloss.web.wicket.component.link;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableFunction;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class AbstractLink<T> extends BootstrapAjaxLink<T> {
	
	private SerializableFunction<AjaxRequestTarget, ?> newEventGenerator;
	
	public <L extends Serializable> AbstractLink(String componentId, IModel<T> model, Type type, SerializableFunction<AjaxRequestTarget, ?> newEventGenerator, IModel<L> labelModel) {
		super(componentId, model, type, labelModel);
		this.newEventGenerator = newEventGenerator;
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		send(this, Broadcast.BUBBLE, newEventGenerator.apply(target));
	}
	
}
