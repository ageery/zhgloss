package com.zixinxi.web.wicket.content.transcription;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.zixinxi.web.wicket.content.lookup.LookupPage;

public class WordLookupLink extends Panel {
	
	public WordLookupLink(String id, IModel<String> displayModel, IModel<String> systemCodeModel, IModel<String> pinyinModel) {
		super(id);
		BookmarkablePageLink<String> link = new BookmarkablePageLink<>("link", LookupPage.class, LookupPage.getPageParameters(systemCodeModel.getObject(), pinyinModel.getObject()));
		add(link);
		link.add(new Label("label", displayModel));
	}
	
}
