package com.zixinxi.web.wicket.content.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.service.WordService;
import com.zixinxi.web.wicket.app.Icons;
import com.zixinxi.web.wicket.component.TitledPage;
import com.zixinxi.web.wicket.content.dictionary.DictionaryPage;
import com.zixinxi.web.wicket.content.gloss.GlossPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

@MountPath("/")
public class HomePage extends TitledPage {

	@SpringBean
	private WordService wordService;
	
	public HomePage(PageParameters parameters) {
		super(parameters);
		
		add(new ActivityPanel("activity"));
		
		add(new BootstrapBookmarkablePageLink<>("dictionaryLink", DictionaryPage.class, Type.Menu)
				.setIconType(Icons.ICON_DICTIONARY)
				.setLabel(new ResourceModel("label.dictionary"))
				.setSize(Size.Large));
		add(new Label("dictionaryDescription", new ResourceModel("dictionaryDescription")));
		
		add(new BootstrapBookmarkablePageLink<>("glossLink", GlossPage.class, Type.Menu)
				.setIconType(Icons.ICON_GLOSS)
				.setLabel(new ResourceModel("label.gloss"))
				.setSize(Size.Large));
		add(new Label("glossDescription", new ResourceModel("glossDescription")));
		
	}

}
