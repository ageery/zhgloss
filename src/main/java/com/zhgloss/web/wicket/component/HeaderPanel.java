package com.zhgloss.web.wicket.component;

import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

public class HeaderPanel extends Border {
	
	public HeaderPanel(String id, IModel<String> titleModel) {
		this(id, tid -> new HeaderTitlePanel(tid, titleModel));
	}

	public HeaderPanel(String id, Function<String, Component> headerFunction) {
		this(id, headerFunction, null);
	}
	
	public HeaderPanel(String id, Function<String, Component> headerFunction, Consumer<Component> postBodyInstantiationProcessor) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		WebMarkupContainer bodyContainer = new WebMarkupContainer("bodyContainer");
		if (postBodyInstantiationProcessor != null) {
			postBodyInstantiationProcessor.accept(bodyContainer);
		}
		addToBorder(bodyContainer, headerFunction.apply("headerContainer"));
	}
	
}
