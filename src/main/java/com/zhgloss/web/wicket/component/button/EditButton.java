package com.zhgloss.web.wicket.component.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableBiFunction;
import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.event.EditEvent;
import com.zhgloss.web.wicket.model.EmptyStringModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

public class EditButton extends AbstractButton {

	public EditButton(String id) {
		this(id, EmptyStringModel.get());
	}
	
	public EditButton(String id, IModel<String> labelModel) {
		this(id, labelModel, Type.Default);
	}
	
	public EditButton(String id, IModel<String> labelModel, Type type) {
		this(id, labelModel, type, Icons.ICON_EDIT);
	}
	
	public EditButton(String id, IModel<String> labelModel, Type type, IconType iconType) {
		this(id, labelModel, type, iconType, (target, form) -> new EditEvent<>(target, form.getDefaultModelObject()));
	}

	public EditButton(String id, IModel<String> labelModel, Type type, IconType iconType, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator) {
		super(id, labelModel, type, newEventGenerator);
		setIconType(iconType);
	}
	
}
