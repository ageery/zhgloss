package com.zhgloss.web.wicket.content.home;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.app.Icons;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.component.HeaderPanel;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.dictionary.DictionaryPage;
import com.zhgloss.web.wicket.content.gloss.GlossPage;
import com.zhgloss.web.wicket.event.SaveEvent;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

@MountPath("/")
public class HomePage extends TitledPage {

	@SpringBean
	private WordService wordService;
	
	private Component recentWords;
	
	public HomePage(PageParameters parameters) {
		super(parameters);
		
		recentWords = new HeaderPanel("panel", cid -> new ActivityHeaderPanel(cid))
				.add(new Label("title", new ResourceModel("label.recently_added_words")))
				.add(new ActivityPanel("activity", 
						Model.of(new WordDetailSearchCriteria()), 
						new SupplierModel<>(() -> ZhGlossSession.get().getUserSettings().getTranscriptionSystem()),
						20));
		add(recentWords);
		
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

	@OnEvent(types = UserSettings.class)
	public void handleUserSettingsSaveEvent(SaveEvent<UserSettings> event) {
		event.getTarget().add(recentWords);
	}

}
