package com.zixinxi.web.wicket.component;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.zixinxi.web.wicket.content.lookup.LookupPage;
import com.zixinxi.web.wicket.content.segment.SegmentPage;
import com.zixinxi.web.wicket.content.transcription.TranscriptionsPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;

public abstract class BasePage extends WebPage implements IAjaxIndicatorAware {

	private static final String LOADER_ID = "ajax-loader-mask";
	
    public BasePage(final PageParameters parameters) {
        super(parameters);
        add(new HtmlTag("html"));
        add(new Label("title", getTitleModel()));
        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), getTitleModel()));
        add(new MetaTag("author", Model.of("author"), new ResourceModel("app.author")));
        add(newNavbar("navbar"));
        add(new FooterPanel("footer"));
        //add(new AjaxLoaderMaskPanel("loader"));
        add(new Icon("loader", FontAwesomeIconType.spinner).setMarkupId(LOADER_ID));
    }
    
    protected IModel<String> getTitleModel() {
    	return new ResourceModel("app.name");
    }

    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);
        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);
        navbar.setBrandName(Model.of("字信息 - Character Info"));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<>(LookupPage.class, new ResourceModel("topmenu.lookup")),
                new NavbarButton<>(TranscriptionsPage.class, new ResourceModel("topmenu.transcriptions")),
                new NavbarButton<>(SegmentPage.class, new ResourceModel("topmenu.segment"))));
        return navbar;
    }

	@Override
	public String getAjaxIndicatorMarkupId() {
		return LOADER_ID;
	}

}
