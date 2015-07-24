package com.zhgloss.web.wicket.component.button;

import org.apache.wicket.model.IModel;

import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.event.EditEvent;
import com.zhgloss.web.wicket.model.EmptyStringModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class EditButton extends AbstractButton {

	public EditButton(String id) {
		this(id, EmptyStringModel.get());
	}
	
	public EditButton(String id, IModel<String> labelModel) {
		this(id, labelModel, Type.Default);
	}
	
	public EditButton(String id, IModel<String> labelModel, Type type) {
		super(id, labelModel, type, (target, form) -> new EditEvent<>(target, form.getDefaultModelObject()));
		setIconType(Icons.ICON_EDIT);
	}
	
}
