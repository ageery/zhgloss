package com.zixinxi.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Op<V> {

	private V value;

	/**
	 * @param value the wrapped value
	 * @return {@link Op} wrapped around {@code value}
	 */
	public static <V> Op<V> of(V value) {
		return new Op<>(value);
	}

	/**
	 * @param value the wrapped value
	 */
	private Op(V value) {
		super();
		this.value = value;
	}

	/**
	 * @param mapper {@link Function} for mapping {@code V} to {@code R}
	 * @return a new {@link Op} object with the value being either:
	 * <ul>
	 * <li>the result of applying {@code mapper} to the {@code value} if {@code value} is not null; or</li>
	 * <li>null if {@code value} is null</li>
	 * </ul>
	 */
	public <R> Op<R> flatMap(Function<V, R> mapper) {
		Objects.requireNonNull(mapper);
		return new Op<>(value == null ? null : mapper.apply(value));
	}

	/**
	 * @return either
	 * <ul>
	 * <li>an empty {@link Optional} if {@code value} is null</li>
	 * <li>an {@link Optional} wrapped around {@code value} if {@code value} is not null</li>
	 * </ul>
	 */
	public Optional<V> toOptional() {
		return Optional.ofNullable(value);
	}

}
