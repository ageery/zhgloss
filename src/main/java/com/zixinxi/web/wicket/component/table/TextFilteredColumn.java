package com.zixinxi.web.wicket.component.table;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.model.IModel;
import org.wicketstuff.lazymodel.LazyModel;

import com.zixinxi.domain.SerializableFunction;

public class TextFilteredColumn<T, S, R, F> extends FunctionColumn<T, S, R> implements IFilteredColumn<T, S> {

	private LazyModel<F> filterModel;
	
	public TextFilteredColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction, LazyModel<F> filterModel) {
		this(displayModel, dataFunction, filterModel, null);
	}
	
	public TextFilteredColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction, LazyModel<F> filterModel, S sortProperty) {
		super(displayModel, dataFunction, sortProperty);
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
