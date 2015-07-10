package com.zixinxi.web.wicket.content.segment;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.service.WordService;
import com.zixinxi.web.wicket.component.BasePage;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.component.button.EditButton;
import com.zixinxi.web.wicket.component.button.SearchButton;
import com.zixinxi.web.wicket.event.EditEvent;
import com.zixinxi.web.wicket.event.SearchEvent;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

@MountPath("/segment")
public class SegmentPage extends BasePage {
	
	@SpringBean
	private WordService service;
	
	private IModel<Boolean> isEditModeModel;
	
	public SegmentPage(PageParameters parameters) {
		super(parameters);
		
		/*
		 * Models.
		 */
		isEditModeModel = Model.of(true);
		IModel<Boolean> isSegmentModeModel = new SupplierModel<>(() -> !isEditModeModel.getObject());
		IModel<String> textModel = Model.of();
		
		add(new Heading("header", Model.of("Segment Text")));

		/*
		 * Form.
		 */
		Form<?> form = new Form<>("form", textModel);
		add(form);
		
		form.add(new FormGroup("textGroup")
			.add(new TextArea<>("text", textModel).add(new InputBehavior()))
			.add(new VisibleModelBehavior(isEditModeModel)));
		
		form.add(new SearchButton("segmentButton", Model.of("Segment")).add(new VisibleModelBehavior(isEditModeModel)));
		form.add(new EditButton("editButton", Model.of("Edit")).add(new VisibleModelBehavior(isSegmentModeModel)));
		
		WebMarkupContainer results = new WebMarkupContainer("results");
		results.setOutputMarkupPlaceholderTag(true);
		results.add(new VisibleModelBehavior(isSegmentModeModel));
		form.add(results);
		
		results.add(new ListView<>("words", new SegmentedWordModel(textModel),
				item -> item.add(new SegmentedWordPanel("word", item.getModel()))));
		
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
