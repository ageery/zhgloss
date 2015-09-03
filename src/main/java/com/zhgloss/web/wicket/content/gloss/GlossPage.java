package com.zhgloss.web.wicket.content.gloss;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;
import org.wicketstuff.minis.model.NotModel;

import com.zhgloss.domain.UserSettings;
import com.zhgloss.service.TranscriptionService;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.event.EditEvent;
import com.zhgloss.web.wicket.event.SearchEvent;

@MountPath("/segment")
public class GlossPage extends TitledPage {
	
	@SpringBean
	private WordService wordService;
	@SpringBean
	private TranscriptionService transcriptionService;
	
	private IModel<Boolean> isEditModeModel;
	
	private Component content;
	
	public GlossPage(PageParameters parameters) {
		super(parameters);
		UserSettings userSettings = ZhGlossSession.get().getUserSettings();
		IModel<SegmentedWordSearchCriteria> searchCriteriaModel = Model.of(
				new SegmentedWordSearchCriteria(userSettings.getCharacterType(), userSettings.getTranscriptionSystem()));
		isEditModeModel = Model.of(true);
		IModel<GlossFormat> formatModel = Model.of(GlossFormat.INLINE);
		
		content = new WebMarkupContainer("content");
		content.setOutputMarkupId(true);
		queue(content);
		
		queue(new GlossFormPanel("form", searchCriteriaModel, formatModel)
				.add(new VisibleModelBehavior(isEditModeModel)));
		queue(new GlossResultsPanel("results", searchCriteriaModel, formatModel)
				.add(new VisibleModelBehavior(new NotModel(isEditModeModel))));
	}
	
	@OnEvent
	public void handleSearchEvent(SearchEvent<String> event) {
		isEditModeModel.setObject(false);
		event.getTarget().add(content);
	}
	
	@OnEvent
	public void handleEditEvent(EditEvent<String> event) {
		isEditModeModel.setObject(true);
		event.getTarget().add(content);
	}
	
}
