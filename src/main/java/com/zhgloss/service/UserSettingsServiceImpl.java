package com.zhgloss.service;

import java.io.IOException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhgloss.domain.UserSettings;

public class UserSettingsServiceImpl implements UserSettingsService {

	@Inject
	private ObjectMapper objectMapper;
	
	public UserSettingsServiceImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public String toJson(UserSettings userSettings) {
		try {
			return objectMapper.writeValueAsString(userSettings);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserSettings toUserSettings(String json) {
		try {
			return objectMapper.readValue(json, UserSettings.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
