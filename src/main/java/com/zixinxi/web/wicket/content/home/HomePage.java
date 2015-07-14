package com.zixinxi.web.wicket.content.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.web.wicket.app.Icons;
import com.zixinxi.web.wicket.component.TitledPage;
import com.zixinxi.web.wicket.content.dictionary.DictionaryPage;
import com.zixinxi.web.wicket.content.gloss.GlossPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;

@MountPath("/")
public class HomePage extends TitledPage {

	public HomePage(PageParameters parameters) {
		super(parameters);
		
		// FIXME: make these into buttons
		BookmarkablePageLink<String> dictionaryLink = new BookmarkablePageLink<>("dictionaryLink", DictionaryPage.class);
		add(dictionaryLink);
		dictionaryLink.add(new Icon("icon", Icons.ICON_DICTIONARY));
		dictionaryLink.add(new Label("label", new ResourceModel("label.dictionary")));
		add(new Label("dictionaryDescription", new ResourceModel("dictionaryDescription")));
		
		BookmarkablePageLink<String> glossLink = new BookmarkablePageLink<>("glossLink", GlossPage.class);
		add(glossLink);
		glossLink.add(new Icon("icon", Icons.ICON_GLOSS));
		glossLink.add(new Label("label", new ResourceModel("label.gloss")));
		add(new Label("glossDescription", new ResourceModel("glossDescription")));
		
	}

}
