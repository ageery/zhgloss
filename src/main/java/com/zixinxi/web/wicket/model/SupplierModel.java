package com.zixinxi.web.wicket.model;

import org.apache.wicket.model.LoadableDetachableModel;

import com.zixinxi.domain.SerializableSupplier;

public class SupplierModel<T> extends LoadableDetachableModel<T> {

	private SerializableSupplier<T> supplier;
	
	public SupplierModel(SerializableSupplier<T> supplier) {
		this.supplier = supplier;
	}
	
	@Override
	protected T load() {
		return supplier.get();
	}

}
