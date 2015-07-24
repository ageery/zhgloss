package com.zhgloss.domain;

public class SortInfo<T> {

	private T property;
	
	private boolean ascending;
	
	public SortInfo(T property) {
		this(property, true);
	}
	
	public SortInfo(T property, boolean ascending) {
		this.property = property;
		this.ascending = ascending;
	}

	public T getProperty() {
		return property;
	}

	public boolean isAscending() {
		return ascending;
	}
	
}
