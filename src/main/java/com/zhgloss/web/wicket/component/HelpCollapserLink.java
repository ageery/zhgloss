package com.zhgloss.web.wicket.component;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;

public class HelpCollapserLink extends Panel {

	public HelpCollapserLink(String id, IModel<String> idModel) {
		super(id, idModel);
		WebMarkupContainer link = new WebMarkupContainer("link");
		add(link);
		link.add(new AttributeModifier("href", 
				new SupplierModel<>(() -> String.format("#%s", idModel.getObject()))));
		link.add(new AttributeModifier("aria-controls", idModel));
		link.add(new Icon("helpIcon", Icons.ICON_HELP));
		add(new VisibleModelBehavior(new SupplierModel<>(() -> !isEmpty(idModel.getObject()))));
	}

}
