package com.zhgloss.web.wicket.component.table;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.zhgloss.domain.SerializableBiFunction;
import com.zhgloss.domain.SerializableProperty;

public class LinkFilteredColumn<T, S, R, Q, F> extends TextFilteredColumn<T, S, R, Q, F> {

	private SerializableBiFunction<String, IModel<T>, ? extends Component> componentFunction;

	public LinkFilteredColumn(IModel<String> displayModel, SerializableProperty<Q, F> filterProperty, SerializableBiFunction<String, IModel<T>, ? extends Component> componentFunction) {
		this(displayModel, filterProperty, null, componentFunction);
	}

	public LinkFilteredColumn(IModel<String> displayModel, SerializableProperty<Q, F> filterProperty, S sortProperty, SerializableBiFunction<String, IModel<T>, ? extends Component> componentFunction) {
		super(displayModel, null, filterProperty, sortProperty);
		this.componentFunction = componentFunction;
	}
	
	@Override
	public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
		cellItem.add(new LinkPanel(componentId, rowModel));
	}

	private class LinkPanel extends Panel {

		public LinkPanel(String id, IModel<T> model) {
			super(id, model);
			add(componentFunction.apply("link", model));
		}
		
	}
	
}
