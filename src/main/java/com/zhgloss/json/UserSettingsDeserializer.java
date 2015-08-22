package com.zhgloss.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.service.TranscriptionService;

public class UserSettingsDeserializer extends JsonDeserializer<UserSettings> {

	private TranscriptionService transcriptionService;
	
	public UserSettingsDeserializer(TranscriptionService transcriptionService) {
		this.transcriptionService = transcriptionService;
	}
	
	@Override
	public UserSettings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String code = node.get(UserSettings.JSON_TRANSCRIPTION_SYSTEM_CODE).asText();
        TranscriptionSystemInfo tsi = transcriptionService.getTranscriptionSystem(code).orElse(null);
        CharacterType charType = CharacterType.valueOf(node.get(UserSettings.JSON_CHAR_TYPE).asText());
        return new UserSettings()
        		.withCharacterType(charType)
        		.withTranscriptionSystem(tsi);
	}

}
