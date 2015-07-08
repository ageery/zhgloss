package com.zixinxi.web.wicket.component.table;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.wicketstuff.lazymodel.LazyColumn;
import org.wicketstuff.lazymodel.LazyModel;

import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

public class OrderedListFilteredColumn<T, S, R extends List<String>, F> extends LazyColumn<T, S, R> implements IFilteredColumn<T, S> {

	private LazyModel<F> filterModel;

	public OrderedListFilteredColumn(IModel<String> displayModel, LazyModel<R> cellModel, LazyModel<F> filterModel) {
		this(displayModel, cellModel, filterModel, null);
	}
	
	public OrderedListFilteredColumn(IModel<String> displayModel, LazyModel<R> cellModel, LazyModel<F> filterModel, S sortProperty) {
		super(displayModel, cellModel, sortProperty);
		this.filterModel = filterModel;
	}

	@Override
	public Component getFilter(String componentId, FilterForm<?> form) {
		return new TextSearchButtonFilter<>(componentId, getFilterModel(form), form, getDisplayModel());
	}

	protected IModel<F> getFilterModel(final FilterForm<?> form) {
		return filterModel.bind(form.getDefaultModel());
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
