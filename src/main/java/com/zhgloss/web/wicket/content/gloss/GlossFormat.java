package com.zhgloss.web.wicket.content.gloss;

public enum GlossFormat {

	INLINE("Inline"),
	TOP_BOTTOM("Separated");
	
	private String displayValue;
	
	GlossFormat(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
}
