package com.zhgloss.web.wicket.component.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.SerializableBiFunction;
import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.event.SaveEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

public class SaveButton extends AbstractButton {

	public SaveButton(String id) {
		this(id, new ResourceModel("label.save"));
	}

	public SaveButton(String id, Form<?> form) {
		this(id, new ResourceModel("label.save"), Type.Primary, Icons.ICON_SAVE, form);
	}
	
	public SaveButton(String id, IModel<String> labelModel) {
		this(id, labelModel, Type.Primary);
	}
	
	public SaveButton(String id, IModel<String> labelModel, Type type) {
		this(id, labelModel, type, Icons.ICON_SAVE);
	}
	
	public SaveButton(String id, IModel<String> labelModel, Type type, IconType iconType) {
		this(id, labelModel, type, iconType, (target, form) -> new SaveEvent<>(target, form.getDefaultModelObject()));
	}
	
	public SaveButton(String id, IModel<String> labelModel, Type type, IconType iconType, Form<?> form) {
		this(id, labelModel, type, iconType, (target, f) -> new SaveEvent<>(target, f.getDefaultModelObject()), form);
	}

	public SaveButton(String id, IModel<String> labelModel, Type type, IconType iconType, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator) {
		this(id, labelModel, type, iconType, newEventGenerator, null);
	}
	
	public SaveButton(String id, IModel<String> labelModel, Type type, IconType iconType, SerializableBiFunction<AjaxRequestTarget, Form<?>, ?> newEventGenerator, Form<?> form) {
		super(id, labelModel, type, form, newEventGenerator);
		setIconType(iconType);
	}
	
}
