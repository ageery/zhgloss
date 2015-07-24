package com.zhgloss.domain;

public class OpFunction<T, R> implements SerializableFunction<T, R> {
	
	private SerializableFunction<T, Op<R>> opFunction;
	private SerializableSupplier<R> defaultValueSupplier;
	
	/**
	 * @param opFunction {@link ISerializableFunction} that returns an 
	 * 		{@link Op} object
	 */
	public OpFunction(SerializableFunction<T, Op<R>> opFunction) {
		this(opFunction, () -> null);
	}

	/**
	 * @param opFunction {@link ISerializableFunction} that returns an 
	 * 		{@link Op} object
	 * @param defaultValueSupplier {@link ISerializableSupplier} that is
	 * 		invoked to provide a default value for the function if it 
	 * 		returns <code>null</code>
	 */
	public OpFunction(SerializableFunction<T, Op<R>> opFunction, SerializableSupplier<R> defaultValueSupplier) {
		this.opFunction = opFunction;
		this.defaultValueSupplier = defaultValueSupplier;
	}

	@Override
	public R apply(T t) {
		return opFunction.apply(t).toOptional().orElse(defaultValueSupplier.get());
	}
	
}
