package com.zixinxi.jooq;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.jooq.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zixinxi.domain.WordDetail;

public class WordDetailConverter implements Converter<Object, WordDetail> {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public WordDetail from(Object json) {
		try {
			return objectMapper.readValue(json.toString(), WordDetail.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<Object> fromType() {
		return Object.class;
	}

	@Override
	public Object to(WordDetail wc) {
		Writer w = new StringWriter();
		try {
			objectMapper.writeValue(w, wc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return w.toString();
	}

	@Override
	public Class<WordDetail> toType() {
		return WordDetail.class;
	}

}
