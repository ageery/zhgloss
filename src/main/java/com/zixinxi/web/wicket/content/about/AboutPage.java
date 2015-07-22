package com.zixinxi.web.wicket.content.about;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.web.wicket.component.HeaderPanel;
import com.zixinxi.web.wicket.component.HeaderTitlePanel;
import com.zixinxi.web.wicket.component.TitledPage;

@MountPath("/about")
public class AboutPage extends TitledPage {

	public AboutPage(PageParameters parameters) {
		super(parameters);
		
		// panels with lists
		
		add(new HeaderPanel("data", hid -> new HeaderTitlePanel(hid, Model.of("Data"))));
			//.add(new Label("label", Model.of("Cedit"))));
		
		add(new HeaderPanel("technology", hid -> new HeaderTitlePanel(hid, Model.of("Technology"))));
		//.add(new Label("label", Model.of("Cedit"))));
		
		// contact
		// fork me
		// technology
		// useful links
		// future
		
	}

}
