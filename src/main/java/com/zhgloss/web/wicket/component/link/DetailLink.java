package com.zhgloss.web.wicket.component.link;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import com.zhgloss.web.wicket.event.DetailEvent;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

public class DetailLink<T> extends AbstractLink<T> {
	
	public <L extends Serializable> DetailLink(String id, IModel<T> model, IModel<L> labelModel) {
		this(id, model, labelModel, Type.Link, null);
	}
	
	public <L extends Serializable> DetailLink(String id, IModel<T> model, IModel<L> labelModel, Type type, IconType iconType) {
		super(id, model, type, target -> new DetailEvent<>(target, model.getObject()), labelModel);
		setIconType(iconType);
	}
	
}
