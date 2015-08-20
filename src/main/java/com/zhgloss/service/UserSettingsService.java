package com.zhgloss.service;

import com.zhgloss.domain.UserSettings;

public interface UserSettingsService {

	String toJson(UserSettings userSettings);
	
	UserSettings toUserSettings(String json);
	
}
