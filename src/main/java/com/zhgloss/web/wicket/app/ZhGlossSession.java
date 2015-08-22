package com.zhgloss.web.wicket.app;

import java.time.Duration;

import javax.servlet.http.Cookie;

import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.service.TranscriptionService;
import com.zhgloss.service.UserSettingsService;

public class ZhGlossSession extends WebSession {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhGlossSession.class);
	private static final String COOKIE_NAME = "user_info";
	
	@SpringBean
	private UserSettingsService userSettingsService;
	@SpringBean
	private TranscriptionService transcriptionService;
	
	private UserSettings userSettings;
	
	public ZhGlossSession(Request request, Response response) {
		super(request);
		Injector.get().inject(this);
		WebRequest webRequest = (WebRequest)RequestCycle.get().getRequest();
		Cookie cookie = webRequest.getCookie(COOKIE_NAME);
		if (cookie == null) {
			WebResponse webResponse = (WebResponse)RequestCycle.get().getResponse();
			userSettings = newUserSettings();
			cookie = new Cookie(COOKIE_NAME, userSettingsService.toJson(userSettings));
			cookie.setMaxAge((int)Duration.ofDays(7).getSeconds());
			webResponse.addCookie(cookie);
		} else {
			try {
				userSettings = userSettingsService.toUserSettings(cookie.getValue());
			} catch (Exception e) {
				LOGGER.warn("Unable to parse cookie value '{}': {}", cookie.getValue(), e);
				userSettings = newUserSettings();
			}
		}
		LOGGER.info("User settings: {}", userSettings);
	}
	
	private  UserSettings newUserSettings() {
		return new UserSettings()
				.withCharacterType(CharacterType.SIMPLFIED)
				.withTranscriptionSystem(transcriptionService.getTranscriptionSystem(TranscriptionSystemInfo.CODE_HANYU_PINYIN).orElse(null));
	}

	public static ZhGlossSession get() {
		return (ZhGlossSession) Session.get();
	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}
	
}
