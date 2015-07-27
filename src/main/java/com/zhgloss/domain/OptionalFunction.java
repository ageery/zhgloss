package com.zhgloss.domain;

import java.util.Optional;

public class OptionalFunction<T, R> implements SerializableFunction<T, R> {
	
	private SerializableFunction<T, Optional<R>> opFunction;
	private SerializableSupplier<R> defaultValueSupplier;
	
	/**
	 * @param opFunction {@link ISerializableFunction} that returns an 
	 * 		{@link Op} object
	 */
	public OptionalFunction(SerializableFunction<T, Optional<R>> opFunction) {
		this(opFunction, () -> null);
	}

	/**
	 * @param opFunction {@link ISerializableFunction} that returns an 
	 * 		{@link Op} object
	 * @param defaultValueSupplier {@link ISerializableSupplier} that is
	 * 		invoked to provide a default value for the function if it 
	 * 		returns <code>null</code>
	 */
	public OptionalFunction(SerializableFunction<T, Optional<R>> opFunction, SerializableSupplier<R> defaultValueSupplier) {
		this.opFunction = opFunction;
		this.defaultValueSupplier = defaultValueSupplier;
	}

	@Override
	public R apply(T t) {
		return opFunction.apply(t).orElse(defaultValueSupplier.get());
	}
	
}
