package com.zhgloss.service;

import com.zhgloss.AppConfig;

public class AppConfigServiceImpl implements AppConfigService {

	private AppConfig config;
	
	public AppConfigServiceImpl(AppConfig config) {
		this.config = config;
	}
	
	@Override
	public AppConfig getConfig() {
		return config;
	}

}
