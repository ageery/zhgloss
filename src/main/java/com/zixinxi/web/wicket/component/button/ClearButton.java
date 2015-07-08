package com.zixinxi.web.wicket.component.button;

import org.apache.wicket.model.IModel;

import com.zixinxi.web.wicket.app.Icons;
import com.zixinxi.web.wicket.event.ClearEvent;
import com.zixinxi.web.wicket.model.EmptyStringModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class ClearButton extends AbstractButton {

	public ClearButton(String id) {
		this(id, EmptyStringModel.get());
	}
	
	public ClearButton(String id, IModel<String> labelModel) {
		this(id, labelModel, Type.Default);
	}
	
	public ClearButton(String id, IModel<String> labelModel, Type type) {
		super(id, labelModel, type, (target, form) -> new ClearEvent(target));
		setIconType(Icons.ICON_CLEAR);
		setDefaultFormProcessing(false);
	}
	
}
