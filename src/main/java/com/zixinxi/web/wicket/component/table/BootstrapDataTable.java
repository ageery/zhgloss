package com.zixinxi.web.wicket.component.table;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

public class BootstrapDataTable<T, S> extends DataTable<T, S> {

	public BootstrapDataTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, long rowsPerPage) {
		super(id, columns, dataProvider, rowsPerPage);
		addBottomToolbar(new BootstrapNavigationToolbar(this));
		addTopToolbar(new HeadersToolbar<S>(this, dataProvider));
		setOutputMarkupId(true);
		add(new CssClassNameAppender("table"));
	}

}
