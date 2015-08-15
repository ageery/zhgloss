package com.zhgloss.domain.external;

import static java.time.Duration.ofSeconds;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Optional.ofNullable;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import com.zhgloss.domain.SerializableFunction;

public class CedictLoadInfo implements Serializable {

	public static final SerializableFunction<CedictLoadInfo, Optional<LocalDateTime>> FUNCTION_START = loadInfo -> ofNullable(loadInfo).flatMap(x -> ofNullable(x.getStart()));
	public static final SerializableFunction<CedictLoadInfo, Optional<LocalDateTime>> FUNCTION_END = loadInfo -> ofNullable(loadInfo).flatMap(x -> ofNullable(x.getEnd()));
	public static final SerializableFunction<CedictLoadInfo, Optional<Integer>> FUNCTION_COUNT = loadInfo -> ofNullable(loadInfo).flatMap(x -> ofNullable(x.getCount()));
	public static final SerializableFunction<CedictLoadInfo, Optional<Duration>> FUNCTION_DURATION = loadInfo -> ofNullable(loadInfo).flatMap(CedictLoadInfo::getDuration);
	
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private int count;
	
	public CedictLoadInfo() {
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
	
	public CedictLoadInfo withStart(LocalDateTime start) {
		setStart(start);
		return this;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public CedictLoadInfo withEnd(LocalDateTime end) {
		setEnd(end);
		return this;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public CedictLoadInfo withCount(int count) {
		setCount(count);
		return this;
	}
	
	public Optional<Duration> getDuration() {
		LocalDateTime start = getStart();
		LocalDateTime end = getEnd();
		return (start == null || end == null) ? Optional.empty() : Optional.of(ofSeconds(SECONDS.between(start, end)));
	}
	
}
