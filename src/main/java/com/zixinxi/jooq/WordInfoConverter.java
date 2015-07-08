package com.zixinxi.jooq;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;

import org.jooq.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zixinxi.domain.CharacterInfo;
import com.zixinxi.domain.WordInfo;

public class WordInfoConverter implements Converter<Object, WordInfo> {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public WordInfo from(Object json) {
		try {
			return new WordInfo(json == null ? Collections.emptyList() : Arrays.asList(objectMapper.readValue(json.toString(), CharacterInfo[].class)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<Object> fromType() {
		return Object.class;
	}

	@Override
	public Object to(WordInfo wc) {
		Writer w = new StringWriter();
		try {
			objectMapper.writeValue(w, wc.getCharacters());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return w.toString();
	}

	@Override
	public Class<WordInfo> toType() {
		return WordInfo.class;
	}

}
