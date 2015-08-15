package com.zhgloss.web.wicket.component;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.zhgloss.domain.SerializableSupplier;

public class BookmarkableLink<T> extends BookmarkablePageLink<T> {

	private SerializableSupplier<PageParameters> pageParametersSupplier;
	
	public <P extends Page> BookmarkableLink(String componentId, Class<P> pageClass, SerializableSupplier<PageParameters> pageParametersSupplier) {
		super(componentId, pageClass);
		this.pageParametersSupplier = pageParametersSupplier;
	}

	@Override
	public PageParameters getPageParameters() {
		return pageParametersSupplier.get();
	}

}
