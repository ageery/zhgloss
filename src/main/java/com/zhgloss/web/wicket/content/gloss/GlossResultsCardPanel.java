package com.zhgloss.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import com.zhgloss.domain.external.SegmentedWord;
import com.zhgloss.web.wicket.component.HeaderButtonTitlePanel;
import com.zhgloss.web.wicket.component.HeaderPanel;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.component.button.EditButton;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size;

public class GlossResultsCardPanel extends Panel {

	public GlossResultsCardPanel(String id, IModel<List<SegmentedWord>> model) {
		super(id, model);
		Form<List<SegmentedWord>> form = new Form<>("form", model);
		add(form);
		
		Border textBorder = new HeaderPanel("header", 
				hid -> new HeaderButtonTitlePanel(hid, Model.of("Text"), 
						bid -> new EditButton(bid, new ResourceModel("label.edit")).setSize(Size.Small)));
		form.add(textBorder);
		
		textBorder.add(new ListView<>("words", model, 
				item -> item.add(new WordPanel("word", item.getModel()))));
	}

}
