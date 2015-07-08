package com.zixinxi.web.wicket.content.segment;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.service.WordService;
import com.zixinxi.web.wicket.component.BasePage;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.component.button.SearchButton;
import com.zixinxi.web.wicket.event.SearchEvent;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

@MountPath("/segment")
public class SegmentPage extends BasePage {
	
	@SpringBean
	private WordService service;
	
	private WebMarkupContainer results;
	
	public SegmentPage(PageParameters parameters) {
		super(parameters);
		add(new Heading("header", Model.of("Segment Text")));
		
		IModel<String> textModel = Model.of();
		
		Form<?> form = new Form<>("form", textModel);
		add(form);
		
		form.add(new FormGroup("textGroup")
			.add(new TextArea<>("text", textModel).add(new InputBehavior())));
		
		form.add(new SearchButton("segmentButton", Model.of("Segment")));
		
		IModel<List<SegmentedWord>> segmentedWordListModel = new SegmentedWordModel(textModel);
		results = new WebMarkupContainer("results");
		results.setOutputMarkupPlaceholderTag(true);
		results.add(new VisibleModelBehavior(new SupplierModel<>(() -> !StringUtils.isEmpty(textModel.getObject()))));
		add(results);
		
		results.add(new ListView<>("words", segmentedWordListModel, 
				item -> item.add(new Label("word", SegmentedWordModels.bindText(item.getModel())))));
		
	}
	
	@OnEvent
	public void handleSearchEvent(SearchEvent<String> event) {
		event.getTarget().add(results);
	}

}
