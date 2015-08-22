package com.zhgloss.web.wicket.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableFunction;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

public class NavbarAjaxLink<T> extends de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarAjaxLink<T> {
	
	private SerializableFunction<AjaxRequestTarget, ?> onClickFunction;
	
	public NavbarAjaxLink(IModel<String> label, SerializableFunction<AjaxRequestTarget, ?> onClickFunction, IconType iconType) {
		super(label);
		this.onClickFunction = onClickFunction;
		setIconType(iconType);
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		send(this, Broadcast.BUBBLE, onClickFunction.apply(target));
	}

}
