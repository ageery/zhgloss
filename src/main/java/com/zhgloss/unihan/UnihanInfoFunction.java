package com.zhgloss.unihan;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang3.StringUtils;

public class UnihanInfoFunction implements Function<XMLStreamReader, UnihanInfo> {

	private static final String PROPERTY_PREFIX = "k";
	private static final int PREFIX_LENGTH = PROPERTY_PREFIX.length();
	private static final String CODE_POINT_PROPERTY_NAME = "cp";
	
	@Override
	public UnihanInfo apply(XMLStreamReader r) {
		Map<String, String> map = new HashMap<>();
		Integer cp = null;
		for (int i = 0; i < r.getAttributeCount(); i++) {
			String name = r.getAttributeLocalName(i);
			if (name.startsWith(PROPERTY_PREFIX)) {
				name = name.substring(PREFIX_LENGTH);
			}
			String value = r.getAttributeValue(i);
			if (CODE_POINT_PROPERTY_NAME.equals(name)) {
				cp = Integer.parseInt(value, 16);
			} else if (!StringUtils.isEmpty(value)) {
				map.put(name, value);
			}
		}
		return new UnihanInfo(new String(Character.toChars(cp)), map);
	}

}
