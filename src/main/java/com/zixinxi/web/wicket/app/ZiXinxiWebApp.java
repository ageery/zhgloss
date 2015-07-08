package com.zixinxi.web.wicket.app;

import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.zixinxi.web.wicket.content.home.HomePage;
import com.zixinxi.web.wicket.theme.ZiXinxiTheme;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.SingleThemeProvider;
import de.agilecoders.wicket.less.BootstrapLess;

public class ZiXinxiWebApp extends WebApplication {
	
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
        BootstrapSettings settings = new BootstrapSettings();
        settings.setThemeProvider(new SingleThemeProvider(new ZiXinxiTheme()));
        Bootstrap.install(this, settings);
        BootstrapLess.install(this);
    }
    
	private void configureComponentInstantiationListeners() {
		getComponentInstantiationListeners()
			.add(new SpringComponentInjector(this, getWebApplicationContext(getServletContext())));
	}
	
	private void configureAnnotationMountScanner() {
		new AnnotatedMountScanner().scanPackage("com.zixinxi.web.wicket.content.**").mount(this);
	}
	
}
