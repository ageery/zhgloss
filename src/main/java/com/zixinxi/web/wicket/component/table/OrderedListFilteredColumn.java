package com.zixinxi.web.wicket.component.table;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.SerializableFunction;
import com.zixinxi.domain.SerializableProperty;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

public class OrderedListFilteredColumn<T, S, R extends List<String>, Q, F> extends TextFilteredColumn<T, S, R, Q, F> implements IFilteredColumn<T, S> {

	public OrderedListFilteredColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction, SerializableProperty<Q, F> filterProperty) {
		this(displayModel, dataFunction, filterProperty, null);
	}
	
	public OrderedListFilteredColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction, SerializableProperty<Q, F> filterProperty, S sortProperty) {
		super(displayModel, dataFunction, filterProperty, sortProperty);
	}

	@Override
	public void populateItem(Item<ICellPopulator<T>> item, String componentId, IModel<T> rowModel) {
		item.add(new OrderedListPanel(componentId, getDataModel(rowModel)));
	}
	
	private static class OrderedListPanel extends Panel {

		public OrderedListPanel(String id, IModel<? extends List<String>> model) {
			super(id, model);
			WebMarkupContainer list = new WebMarkupContainer("list");
			list.add(new CssClassNameAppender(new SupplierModel<>(() -> model.getObject().size() <= 1 ? "list-unstyled" : "")));
			add(list);
			list.add(new ListView<>("item", model, item -> item.add(new Label("label", item.getModel()))));
		}
		
	}
	
}
