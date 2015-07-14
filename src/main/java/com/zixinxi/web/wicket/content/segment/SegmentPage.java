package com.zixinxi.web.wicket.content.segment;

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
import com.zixinxi.web.wicket.component.BasePage;
import com.zixinxi.web.wicket.event.EditEvent;
import com.zixinxi.web.wicket.event.SearchEvent;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

@MountPath("/segment")
public class SegmentPage extends BasePage {
	
	@SpringBean
	private WordService wordService;
	@SpringBean
	private TranscriptionService transcriptionService;
	
	private IModel<Boolean> isEditModeModel;
	
	public SegmentPage(PageParameters parameters) {
		super(parameters);
		
		/*
		 * Models.
		 */
		IModel<SegmentedWordSearchCriteria> searchCriteriaModel = Model.of(new SegmentedWordSearchCriteria(SIMPLFIED, 
				transcriptionService.getTranscriptionSystem(TranscriptionSystemInfo.CODE_HANYU_PINYIN).orElse(null)));
		isEditModeModel = Model.of(true);
		
//		IModel<String> textModel = Model.of();
//		IModel<CharacterType> charTypeModel = Model.of(SIMPLFIED);
//		IModel<TranscriptionSystemInfo> transcriptionSystemModel = new TranscriptionSystemInfoModel(CODE_HANYU_PINYIN);
		
		add(new Heading("header", Model.of("Segment Text")));
		
		add(new SegmentFormPanel("form", searchCriteriaModel).add(new VisibleModelBehavior(isEditModeModel)));

//		/*
//		 * Form.
//		 */
//		Form<?> form = new BootstrapForm<>("form", textModel);
//		add(form);
//		
//		WebMarkupContainer searchContainer = new WebMarkupContainer("searchContainer");
//		form.add(searchContainer);
//
//		FormGroup typeGroup = new FormGroup("typeGroup");
//		searchContainer.add(typeGroup);
//		FormComponent<CharacterType> typeChoice = new BootstrapSelect<>("types", 
//				charTypeModel, 
//				new CharacterTypeListModel(),
//				new ChoiceRenderer<CharacterType>(CharacterType::getDisplayValue, (ct, index) -> ct.getDbValue()))
//				.setLabel(Model.of("Character Type"));
//		typeGroup.add(typeChoice);
//		
//		FormGroup systemGroup = new FormGroup("systemGroup");
//		searchContainer.add(systemGroup);
//		FormComponent<TranscriptionSystemInfo> systemChoice = new BootstrapSelect<>("systems", 
//				transcriptionSystemModel, 
//				new TranscriptionSystemInfoListModel(),
//				new ChoiceRenderer<>(ts -> ts.getName(), (ts, index) -> ts.getCode()))
//				.setLabel(Model.of("Transcription System"));
//		systemGroup.add(systemChoice);
//		
//		searchContainer.add(new FormGroup("textGroup")
//			.add(new TextArea<>("text", textModel).add(new InputBehavior()))
//			.add(new VisibleModelBehavior(isEditModeModel)));
//		
//		searchContainer.add(new SearchButton("segmentButton", Model.of("Segment"), Type.Primary)
//			.add(new VisibleModelBehavior(isEditModeModel)));
//		form.add(new EditButton("editButton", Model.of("Edit")).add(new VisibleModelBehavior(isSegmentModeModel)));
		
		add(new SegmentedWordResultsPanel("results", searchCriteriaModel)
				.add(new VisibleModelBehavior(new NotModel(isEditModeModel))));
		
//		WebMarkupContainer results = new WebMarkupContainer("results");
//		results.setOutputMarkupPlaceholderTag(true);
//		results.add(new VisibleModelBehavior(isSegmentModeModel));
//		form.add(results);
//		
//		results.add(new ListView<>("words", new SegmentedWordListModel(textModel, charTypeModel, transcriptionSystemModel, 10),
//				item -> item.add(new SegmentedWordPanel("word", item.getModel()))));
		
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();
		isEditModeModel.detach();
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
