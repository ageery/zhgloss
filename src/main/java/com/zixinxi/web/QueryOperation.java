package com.zixinxi.web;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QueryOperation {

	EXACT("e"),
	STARTS_WITH("s"),
	CONTAINS("c");
	
	private static final Map<String, QueryOperation> MAP = Stream.of(QueryOperation.values()).collect(Collectors.toMap(QueryOperation::getUiValue, Function.identity()));
	
	private String uiValue;
	
	QueryOperation(String uiValue) {
		this.uiValue = uiValue;
	}

	public String getUiValue() {
		return uiValue;
	}
	
	public static QueryOperation findByUiValue(String uiValue) {
		return MAP.get(uiValue);
	}
	
}
