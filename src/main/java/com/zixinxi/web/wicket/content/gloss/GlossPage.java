package com.zixinxi.web.wicket.content.gloss;

import static com.zixinxi.domain.CharacterType.SIMPLFIED;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;
import org.wicketstuff.minis.model.NotModel;

import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.service.TranscriptionService;
import com.zixinxi.service.WordService;
import com.zixinxi.web.wicket.component.TitledPage;
import com.zixinxi.web.wicket.event.EditEvent;
import com.zixinxi.web.wicket.event.SearchEvent;

@MountPath("/segment")
public class GlossPage extends TitledPage {
	
	@SpringBean
	private WordService wordService;
	@SpringBean
	private TranscriptionService transcriptionService;
	
	private IModel<Boolean> isEditModeModel;
	
	public GlossPage(PageParameters parameters) {
		super(parameters);
		
		IModel<SegmentedWordSearchCriteria> searchCriteriaModel = Model.of(
				new SegmentedWordSearchCriteria(SIMPLFIED, 
						transcriptionService.getTranscriptionSystem(TranscriptionSystemInfo.CODE_HANYU_PINYIN)
							.orElse(null)));
		isEditModeModel = Model.of(true);
		
		add(new GlossFormPanel("form", searchCriteriaModel).add(new VisibleModelBehavior(isEditModeModel)));
		add(new GlossResultsPanel("results", searchCriteriaModel)
				.add(new VisibleModelBehavior(new NotModel(isEditModeModel))));
	}
	
	@OnEvent
	public void handleSearchEvent(SearchEvent<String> event) {
		isEditModeModel.setObject(false);
		event.getTarget().add(this);
	}
	
	@OnEvent
	public void handleEditEvent(EditEvent<String> event) {
		isEditModeModel.setObject(true);
		event.getTarget().add(this);
	}
	
}
