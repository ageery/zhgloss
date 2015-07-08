package com.zixinxi.web.wicket.component.table;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.model.IModel;
import org.wicketstuff.lazymodel.LazyColumn;
import org.wicketstuff.lazymodel.LazyModel;

public class TextFilteredColumn<T, S, R, F> extends LazyColumn<T, S, R> implements IFilteredColumn<T, S> {

	private LazyModel<F> filterModel;
	
	public TextFilteredColumn(IModel<String> displayModel, LazyModel<R> cellModel, LazyModel<F> filterModel) {
		this(displayModel, cellModel, filterModel, null);
	}
	
	public TextFilteredColumn(IModel<String> displayModel, LazyModel<R> cellModel, LazyModel<F> filterModel, S sortProperty) {
		super(displayModel, cellModel, sortProperty);
		this.filterModel = filterModel;
	}

	@Override
	public Component getFilter(String componentId, FilterForm<?> form) {
		return new TextSearchButtonFilter<>(componentId, getFilterModel(form), form, getDisplayModel());
	}

	protected IModel<F> getFilterModel(final FilterForm<?> form)
	{
		return filterModel.bind(form.getDefaultModel());
	}

}
