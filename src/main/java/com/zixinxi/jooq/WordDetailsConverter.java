package com.zixinxi.jooq;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;

import org.jooq.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zixinxi.domain.WordDetail;
import com.zixinxi.domain.WordDetails;

public class WordDetailsConverter implements Converter<Object, WordDetails> {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public WordDetails from(Object json) {
		try {
			return new WordDetails(json == null ? Collections.emptyList() : Arrays.asList(objectMapper.readValue(json.toString(), WordDetail[].class)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<Object> fromType() {
		return Object.class;
	}

	@Override
	public Object to(WordDetails wc) {
		Writer w = new StringWriter();
		try {
			objectMapper.writeValue(w, wc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return w.toString();
	}

	@Override
	public Class<WordDetails> toType() {
		return WordDetails.class;
	}

}
