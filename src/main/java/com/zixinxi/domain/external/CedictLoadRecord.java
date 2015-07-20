package com.zixinxi.domain.external;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CedictLoadRecord implements Serializable {

	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private int count;
	
	public CedictLoadRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public CedictLoadRecord withStart(LocalDateTime start) {
		setStart(start);
		return this;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public CedictLoadRecord withEnd(LocalDateTime end) {
		setEnd(end);
		return this;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public CedictLoadRecord withCount(int count) {
		setCount(count);
		return this;
	}
	
}
