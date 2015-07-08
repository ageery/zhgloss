package com.zixinxi.domain;

import java.util.List;

public class SegmentDetail {

	private String text;
	
	private List<WordDetail> details;
	
	public SegmentDetail() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public SegmentDetail withText(String text) {
		setText(text);
		return this;
	}

	public List<WordDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WordDetail> details) {
		this.details = details;
	}
	
	public SegmentDetail withDetails(List<WordDetail> details) {
		setDetails(details);
		return this;
	}
	
}
