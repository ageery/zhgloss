package com.zhgloss.web.wicket.app;

import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.zhgloss.web.wicket.content.home.HomePage;
import com.zhgloss.web.wicket.theme.ZhGlossTheme;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.SingleThemeProvider;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

public class ZhGlossWebApp extends WebApplication {
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		configureAnnotationMountScanner();
		configureComponentInstantiationListeners();
        configureBootstrap();
	}
	
    private void configureBootstrap() {
    	WicketWebjars.install(this, new WebjarsSettings().useCdnResources(true));
        BootstrapSettings settings = new BootstrapSettings();
        settings.setThemeProvider(new SingleThemeProvider(new ZhGlossTheme()));
        settings.useCdnResources(true);
        Bootstrap.install(this, settings);
        BootstrapLess.install(this);
    }
    
	private void configureComponentInstantiationListeners() {
		getComponentInstantiationListeners()
			.add(new SpringComponentInjector(this, getWebApplicationContext(getServletContext())));
	}
	
	private void configureAnnotationMountScanner() {
		new AnnotatedMountScanner().scanPackage("com.zhgloss.web.wicket.content.**").mount(this);
	}
	
}
