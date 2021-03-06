package com.zhgloss.web.wicket.component;

import static com.zhgloss.web.wicket.app.Icons.ICON_ABOUT;
import static com.zhgloss.web.wicket.app.Icons.ICON_CONTACT;
import static com.zhgloss.web.wicket.app.Icons.ICON_DICTIONARY;
import static com.zhgloss.web.wicket.app.Icons.ICON_GLOSS;
import static de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents.transform;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.domain.UserSettings;
import com.zhgloss.service.UserSettingsService;
import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.content.about.AboutPage;
import com.zhgloss.web.wicket.content.dictionary.DictionaryPage;
import com.zhgloss.web.wicket.content.gloss.GlossPage;
import com.zhgloss.web.wicket.event.EditEvent;
import com.zhgloss.web.wicket.event.SaveEvent;
import com.zhgloss.web.wicket.model.EmptyStringModel;
import com.zhgloss.web.wicket.model.MailToModel;
import com.zhgloss.web.wicket.model.UserSettingsShortModel;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;

public abstract class BasePage extends WebPage implements IAjaxIndicatorAware {

	private static final String LOADER_ID = "ajax-loader-mask";
	
	@SpringBean
	private UserSettingsService service;
	
	private Component navbar;
	private UserSettingsModal modal;
	
	public BasePage() {
		this(new PageParameters());
	}
	
    public BasePage(final PageParameters parameters) {
        super(parameters);
        add(new HtmlTag("html"));
        add(new Label("title", getTitleModel()));
        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), getTitleModel()));
        add(new MetaTag("author", Model.of("author"), new ResourceModel("app.author")));
        navbar = newNavbar("navbar");
        add(navbar);
        add(new FooterPanel("footer"));
        add(new Icon("loader", FontAwesomeIconType.spinner).setMarkupId(LOADER_ID));
        add(new ResourceLink<>("favIcon", new PackageResourceReference(BasePage.class, "res/favicon.png")));
        
        modal = new UserSettingsModal("userSettingsModal", new SupplierModel<>(() -> ZhGlossSession.get().getUserSettings()));
        add(modal);
        
    }
    
    protected IModel<String> getTitleModel() {
    	return new ResourceModel("page.title", new ResourceModel("app.name"));
    }

    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);
        navbar.setOutputMarkupId(true);
        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);
        navbar.setBrandName(new ResourceModel("app.branding"));
        navbar.addComponents(transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<>(DictionaryPage.class, 
                		new ResourceModel("label.dictionary"))
                	.setIconType(ICON_DICTIONARY),
                new NavbarButton<>(GlossPage.class, 
                		new ResourceModel("label.gloss"))
                	.setIconType(ICON_GLOSS),
                new NavbarButton<>(AboutPage.class, 
                		new ResourceModel("label.about"))
                	.setIconType(ICON_ABOUT)));
        navbar.addComponents(transform(Navbar.ComponentPosition.RIGHT,
        		new NavbarAjaxLink<>(new UserSettingsShortModel(), 
        				target -> new EditEvent<>(target, ZhGlossSession.get().getUserSettings()), 
        				Icons.ICON_SETTINGS)));
//        		new NavbarExternalLink(new MailToModel(new ResourceModel("app.contact")))
//        			.setLabel(new ResourceModel("label.contact"))
//        			.setIconType(ICON_CONTACT)));
        return navbar;
    }

	@Override
	public String getAjaxIndicatorMarkupId() {
		return LOADER_ID;
	}
	
	@OnEvent(types = UserSettings.class)
	public void handleEditUserSettings(EditEvent<UserSettings> event) {
		modal.show(event.getTarget());
	}

	@OnEvent(types = UserSettings.class)
	public void basePageHandleUserSettingsSaveEvent(SaveEvent<UserSettings> event) {
		modal.close(event.getTarget());
		ZhGlossSession.get().saveUserSettings();
		event.getTarget().add(navbar);
	}
	
}
