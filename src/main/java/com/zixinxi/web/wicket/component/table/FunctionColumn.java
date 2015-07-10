package com.zixinxi.web.wicket.component.table;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.zixinxi.domain.SerializableFunction;
import com.zixinxi.web.wicket.model.LambdaModel;

public class FunctionColumn<T, S, R> extends AbstractColumn<T, S> {

	private SerializableFunction<T, R> dataFunction;
	
	public FunctionColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction) {
		this(displayModel, dataFunction, null);
	}

	public FunctionColumn(IModel<String> displayModel, SerializableFunction<T, R> dataFunction, S sortProperty) {
		super(displayModel, sortProperty);
		this.dataFunction = dataFunction;
	}

	@Override
	public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
		cellItem.add(new Label(componentId, getDataModel(rowModel)));
	}
	
	protected IModel<R> getDataModel(IModel<T> rowModel) {
		return new LambdaModel<>(rowModel, dataFunction);
	}

}
