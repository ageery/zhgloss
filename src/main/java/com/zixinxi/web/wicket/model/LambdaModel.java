package com.zixinxi.web.wicket.model;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.zixinxi.domain.SerializableBiConsumer;
import com.zixinxi.domain.SerializableFunction;
import com.zixinxi.domain.SerializableProperty;

/**
 * {@link LoadableDetachableModel} that wraps an {@link IModel} and applies an 
 * {@link Function} to the wrapped model to get the value for the
 * {@link #load()} function. 
 * <p/>
 * An optional {@link BiConsumer} can be provided that will be 
 * called with the object of the wrapped model and the new object value 
 * when the {@link #setObject()} method is called. 
 * 
 * @param <T> type of the wrapped {@link IModel}
 * @param <R> type of the {@link LambdaModel}
 */
public class LambdaModel<T, R> extends LoadableDetachableModel<R> {
	
	private IModel<T> wrappedModel;
	private SerializableFunction<T, R> loadHandler;
	private SerializableBiConsumer<T, R> setObjectHandler;

	/**
	 * @param wrappedModel {@link IModel} that wraps the value which will be applied to the {@code loadHandler} {@link Function}
	 * @param loadHandler {@link Function} that is invoked to provide the value for the {@code #load()} method
	 */
	public LambdaModel(IModel<T> wrappedModel, SerializableFunction<T, R> loadHandler) {
		this(wrappedModel, loadHandler, (t, r) -> {});
	}

	public LambdaModel(IModel<T> wrappedModel, SerializableProperty<T, R> propertyHandler) {
		this(wrappedModel, propertyHandler.getGetter(), propertyHandler.getSetter());
	}
	
	/**
	 * @param wrappedModel {@link IModel} that wraps the value which will be applied to the {@code loadHandler} {@link Function}
	 * @param loadHandler {@link Function} that is invoked to provide the value for the {@code #load()} method
	 * @param setObjectHandler {@link BiFunction} that is invoked when the {@code #setObject()} method is called
	 */
	public LambdaModel(IModel<T> wrappedModel, SerializableFunction<T, R> loaderFunction, SerializableBiConsumer<T, R> setObjectHandler) {
		super();
		Objects.requireNonNull(wrappedModel);
		Objects.requireNonNull(loaderFunction);
		Objects.requireNonNull(setObjectHandler);
		this.wrappedModel = wrappedModel;
		this.loadHandler = loaderFunction;
		this.setObjectHandler = setObjectHandler;
	}

	@Override
	protected R load() {
		return loadHandler.apply(wrappedModel.getObject());
	}
	
	@Override
	public void setObject(R r) {
		super.setObject(r);
		setObjectHandler.accept(wrappedModel.getObject(), r);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		wrappedModel.detach();
	}
	
}

