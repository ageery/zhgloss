package com.zhgloss.web.wicket.content.about;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zhgloss.web.wicket.component.HeaderPanel;
import com.zhgloss.web.wicket.component.HeaderTitlePanel;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.about.ForkMeLink.Location;

@MountPath("/about")
public class AboutPage extends TitledPage {

	public AboutPage(PageParameters parameters) {
		super(parameters);
		add(new ForkMeLink("fork", Model.of("https://github.com/ageery/zixinxi"), Location.LOWER_RIGHT));
		add(new HeaderPanel("data", hid -> new HeaderTitlePanel(hid, Model.of("Data"))));
		add(new HeaderPanel("technology", hid -> new HeaderTitlePanel(hid, Model.of("Technology"))));
	}

}
