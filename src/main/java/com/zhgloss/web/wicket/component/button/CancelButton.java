package com.zhgloss.web.wicket.component.button;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.event.CancelEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class CancelButton extends AbstractButton {

	public CancelButton(String id) {
		this(id, new ResourceModel("label.cancel"));
	}

	public CancelButton(String id, Form<?> form) {
		this(id, new ResourceModel("label.cancel"), Type.Default, form);
	}
	
	public CancelButton(String id, IModel<String> labelModel) {
		this(id, labelModel, Type.Default);
	}
	
	public CancelButton(String id, IModel<String> labelModel, Type type) {
		this(id, labelModel, type, null);
	}

	public CancelButton(String id, IModel<String> labelModel, Type type, Form<?> form) {
		super(id, labelModel, type, form, (target, f) -> new CancelEvent(target));
		setIconType(Icons.ICON_CANCEL);
		setDefaultFormProcessing(false);
	}
	
}
