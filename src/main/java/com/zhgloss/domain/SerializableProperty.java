package com.zhgloss.domain;

import java.io.Serializable;

public interface SerializableProperty<T, R> extends Serializable {

	public static <T, R> SerializableProperty<T, R> of(SerializableFunction<T, R> getter, SerializableBiConsumer<T, R> setter) {
		return new SerializablePropertyImpl<>(getter, setter);
	}
	
	SerializableFunction<T, R> getGetter();
	SerializableBiConsumer<T, R> getSetter();
	
}
