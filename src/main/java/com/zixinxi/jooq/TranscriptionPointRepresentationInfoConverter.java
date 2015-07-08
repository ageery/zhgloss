package com.zixinxi.jooq;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

import org.jooq.Converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zixinxi.domain.TranscriptionPointInfo;
import com.zixinxi.domain.TranscriptionPointRepresentationInfo;

public class TranscriptionPointRepresentationInfoConverter implements Converter<Object, TranscriptionPointRepresentationInfo> {

	private static final TypeReference<Map<String, TranscriptionPointInfo>> MAP_TYPE 
       = new TypeReference<Map<String, TranscriptionPointInfo>>() {};
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public TranscriptionPointRepresentationInfo from(Object json) {
		try {
//			java.util.Map<String, Object> m = objectMapper.readValue(json.toString(), Map.class);
//			Map m2 = m.entrySet().stream()
//					.collect(Collectors.toMap(e -> e.getKey(), 
//							Unchecked.function(e -> 
//							objectMapper.readValue(e.getValue().toString(), TranscriptionPointRepresentationInfo.class))));
//			return new TranscriptionPointRepresentationInfo(m2);
			return new TranscriptionPointRepresentationInfo(json == null ? Collections.emptyMap() : objectMapper.readValue(json.toString(), MAP_TYPE));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<Object> fromType() {
		return Object.class;
	}

	@Override
	public Object to(TranscriptionPointRepresentationInfo wc) {
		Writer w = new StringWriter();
		try {
			objectMapper.writeValue(w, wc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return w.toString();
	}

	@Override
	public Class<TranscriptionPointRepresentationInfo> toType() {
		return TranscriptionPointRepresentationInfo.class;
	}

}
