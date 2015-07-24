package com.zhgloss.domain;

class SerializablePropertyImpl<T, R> implements SerializableProperty<T, R> {

	private SerializableFunction<T, R> getter;
	private SerializableBiConsumer<T, R> setter;
	
	public SerializablePropertyImpl(SerializableFunction<T, R> getter, SerializableBiConsumer<T, R> setter) {
		this.getter = getter;
		this.setter = setter;
	}
	
	@Override
	public SerializableFunction<T, R> getGetter() {
		return getter;
	}

	@Override
	public SerializableBiConsumer<T, R> getSetter() {
		return setter;
	}

}
