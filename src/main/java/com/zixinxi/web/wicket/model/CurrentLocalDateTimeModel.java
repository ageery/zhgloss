package com.zixinxi.web.wicket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.wicket.model.LoadableDetachableModel;

public class CurrentLocalDateTimeModel extends LoadableDetachableModel<String> {

	private String formatString;
	
	public CurrentLocalDateTimeModel(String formatString) {
		super();
		this.formatString = formatString;
	}
	
	@Override
	protected String load() {
		return DateTimeFormatter.ofPattern(formatString).format(LocalDateTime.now());
	}

}
