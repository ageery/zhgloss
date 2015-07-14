package com.zixinxi.web.wicket.content.gloss;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.component.button.EditButton;

public class GlossResultsPanel extends Panel {

	public GlossResultsPanel(String id, IModel<SegmentedWordSearchCriteria> model) {
		super(id, model);
		
		/*
		 * Back to editing functionality.
		 */
		Form<SegmentedWordSearchCriteria> form = new Form<>("form", model);
		add(form);
		form.add(new EditButton("button", Model.of("Edit")));
		
		/*
		 * Results.
		 */
		add(new ListView<>("words", new SegmentedWordListModel(model),
				item -> item.add(new WordPanel("word", item.getModel()))));
	}

}