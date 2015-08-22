package com.zhgloss;

import static com.zhgloss.web.ZhGlossWebConstants.REST_PATH;
import static org.jooq.SQLDialect.POSTGRES_9_4;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.sql.DataSource;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.WicketProperties;
import com.zhgloss.json.UserSettingsDeserializer;
import com.zhgloss.repo.CedictWordRepo;
import com.zhgloss.repo.CedictWordRepoImpl;
import com.zhgloss.repo.TranscriptionRepo;
import com.zhgloss.repo.TranscriptionRepoImpl;
import com.zhgloss.repo.WordRepo;
import com.zhgloss.repo.WordRepoImpl;
import com.zhgloss.service.AppConfigService;
import com.zhgloss.service.AppConfigServiceImpl;
import com.zhgloss.service.TranscriptionService;
import com.zhgloss.service.TranscriptionServiceImpl;
import com.zhgloss.service.UserSettingsService;
import com.zhgloss.service.UserSettingsServiceImpl;
import com.zhgloss.service.WordService;
import com.zhgloss.service.WordServiceImpl;
import com.zhgloss.web.wicket.app.ZhGlossWebApp;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class ZhGlossApp extends SpringBootServletInitializer {

	@Inject
	private DataSource dataSource;
	@Inject
	private AppConfig appConfig;
	
    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(ZhGlossApp.class);
    	app.run(args);
    }
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(ZhGlossApp.class);
	}

	@Bean
    public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(UserSettings.class, new UserSettingsDeserializer(getTranscriptionService()));
		objectMapper.registerModule(module);
		return objectMapper;
    }
    
    @Bean
    public DSLContext getDslContext() {
    	return DSL.using(dataSource, POSTGRES_9_4);
    }
    
    @Bean
    public AppConfigService getAppConfigService() {
    	return new AppConfigServiceImpl(appConfig);
    }
    
    @Bean
    public WordRepo getWordDetailRepo() {
    	return new WordRepoImpl(getDslContext(), getObjectMapper());
    }
    
    @Bean
    public WordService getWordService() {
    	return new WordServiceImpl(getWordDetailRepo(), getCedictWordRepo());
    }
    
    @Bean
    public CedictWordRepo getCedictWordRepo() {
    	return new CedictWordRepoImpl(dataSource, System.getProperty("java.io.tmpdir"), getDslContext());
    }

    @Bean
    public TranscriptionRepo getTranscriptionRepo() {
    	return new TranscriptionRepoImpl(getDslContext());
    }
    
    @Bean
    public TranscriptionService getTranscriptionService() {
    	return new TranscriptionServiceImpl(getTranscriptionRepo());
    }
    
    @Bean
    public CedictDataRefreshRunner getCedictDataLoader() {
    	return new CedictDataRefreshRunner(getWordService());
    }
    
    @Bean
    public WicketProperties getWicketProperties() {
    	return new WicketProperties();
    }

    @Bean
    public UserSettingsService getUserSettingsService() {
    	return new UserSettingsServiceImpl(getObjectMapper());
    }
    
    @Bean
    public FilterRegistrationBean getWicketFiler() {
    	FilterRegistrationBean registration = new FilterRegistrationBean();
    	WebApplication application = new ZhGlossWebApp();
        WicketFilter wicketFilter = new WicketFilter(application);
        application.setConfigurationType(getWicketProperties().getConfiguration());
        wicketFilter.setFilterPath("/");
        registration.addInitParameter(WicketFilter.IGNORE_PATHS_PARAM, REST_PATH + "/*");
        registration.setFilter(wicketFilter);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
    
    @Bean
    public SessionTrackingConfigListener sessionTrackingConfigListener() {
    	return new SessionTrackingConfigListener();
    }
    
    private static class SessionTrackingConfigListener implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			servletContext.setSessionTrackingModes(new HashSet<>(Arrays.asList(SessionTrackingMode.COOKIE)));
		}
	
	}
    
}
