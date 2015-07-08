package com.zixinxi.web.wicket.component;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.SerializableConsumer;

public class ListView<T> extends org.apache.wicket.markup.html.list.ListView<T> {

	private SerializableConsumer<ListItem<T>> consumer;
	
	public ListView(String id, IModel<? extends List<? extends T>> model, SerializableConsumer<ListItem<T>> consumer) {
		super(id, model);
		this.consumer = consumer;
	}

	@Override
	protected void populateItem(ListItem<T> item) {
		consumer.accept(item);
	}

}
