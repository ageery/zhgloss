package com.zhgloss.web.wicket.model;

import org.apache.wicket.Application;
import org.apache.wicket.model.LoadableDetachableModel;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.web.wicket.app.ZhGlossSession;

public class UserSettingsShortModel extends LoadableDetachableModel<String> {

	@Override
	protected String load() {
		UserSettings userSettings = ZhGlossSession.get().getUserSettings();
		return String.format("%s \u2022 %s", 
				getCharacterTypeName(userSettings.getCharacterType()), 
				getTranscriptionSystemName(userSettings.getTranscriptionSystem()));
	}
	
	private String getCharacterTypeName(CharacterType charType) {
		return Application.get()
				.getResourceSettings()
				.getLocalizer()
				.getString(CharacterType.SIMPLFIED.equals(charType) ? "label.simp" : "label.trad", null, null, null, null, "?");
	}
	
	private String getTranscriptionSystemName(TranscriptionSystemInfo transcriptionSystem) {
		return transcriptionSystem.getShortName();
	}

}
