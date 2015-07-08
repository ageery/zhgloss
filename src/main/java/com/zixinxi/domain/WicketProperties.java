package com.zixinxi.domain;

import org.apache.wicket.RuntimeConfigurationType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wicket")
public class WicketProperties {
	
	private RuntimeConfigurationType configuration;
	
	public WicketProperties() {
		super();
	}

	public RuntimeConfigurationType getConfiguration() {
		return configuration;
	}

	public void setConfiguration(RuntimeConfigurationType configuration) {
		this.configuration = configuration;
	}
	
}
