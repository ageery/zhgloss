package com.zhgloss.web.wicket.component.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableBiFunction;
import com.zhgloss.web.wicket.event.ValidationErrorEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;

public class AbstractButton extends BootstrapAjaxButton {
	
	private SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator;
	private IModel<String> titleModel;

	public AbstractButton(String componentId, IModel<String> model, Type type, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator) {
		this(componentId, model, type, null, newEventGenerator);
	}
	
	public AbstractButton(String componentId, IModel<String> model, Type type, Form<?> form, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator) {
		this(componentId, model, type, form, newEventGenerator, model);
	}
	
	public AbstractButton(String componentId, IModel<String> model, Type type, Form<?> form, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator, IModel<String> titleModel) {
		super(componentId, model, form, type);
		this.newEventGenerator = newEventGenerator;
		this.titleModel = titleModel;
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		send(this, Broadcast.BUBBLE, newEventGenerator.apply(target, form));
	}
	
	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		send(form, Broadcast.BREADTH, new ValidationErrorEvent(target));
	}
	
	protected IModel<String> getTitleModel() {
		return titleModel;
	}
	
}
