package com.zhgloss.web.wicket.component.table;

import java.util.function.Function;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableProperty;
import com.zhgloss.web.wicket.model.LambdaModel;

public class TextFilteredColumn<T, S, R, Q, F> extends FunctionColumn<T, S, R> implements IFilteredColumn<T, S> {

	private SerializableProperty<Q, F> filterProperty;
	
	public TextFilteredColumn(IModel<String> displayModel, Function<T, R> dataFunction, SerializableProperty<Q, F> filterProperty) {
		this(displayModel, dataFunction, filterProperty, null);
	}
	
	public TextFilteredColumn(IModel<String> displayModel, Function<T, R> dataFunction, SerializableProperty<Q, F> filterProperty, S sortProperty) {
		super(displayModel, dataFunction, sortProperty);
		this.filterProperty = filterProperty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Component getFilter(String componentId, FilterForm<?> form) {
		return new TextSearchButtonFilter<>(componentId, getFilterModel((FilterForm<Q>)form), form, getDisplayModel());
	}

	protected IModel<F> getFilterModel(final FilterForm<Q> form) {
		return new LambdaModel<>(form.getModel(), filterProperty.getGetter(), filterProperty.getSetter());
	}

}
