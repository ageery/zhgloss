package com.zixinxi.repo;

import org.jooq.DSLContext;

public abstract class AbstractRepoImpl {

	private DSLContext context;
	
	protected AbstractRepoImpl(DSLContext context) {
		super();
		this.context = context;
	}

	protected DSLContext getContext() {
		return context;
	}
	
}
