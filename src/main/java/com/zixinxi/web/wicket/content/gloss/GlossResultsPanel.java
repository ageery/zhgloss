package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.button.EditButton;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class GlossResultsPanel extends Panel {

	public GlossResultsPanel(String id, IModel<SegmentedWordSearchCriteria> model, IModel<GlossFormat> formatModel) {
		super(id, model);
		
		/*
		 * Back to editing functionality.
		 */
		Form<SegmentedWordSearchCriteria> form = new Form<>("form", model);
		add(form);
		form.add(new EditButton("button", Model.of("Edit"), Type.Primary));
		
		IModel<List<SegmentedWord>> segmentedWordListModel = new SegmentedWordListModel(model);
		
		/*
		 * Results.
		 */
		add(new GlossResultsCardPanel("cardResults", segmentedWordListModel)
				.setOutputMarkupPlaceholderTag(true)
				.add(new VisibleModelBehavior(new SupplierModel<>(() -> GlossFormat.INLINE.equals(formatModel.getObject())))));
		add(new GlossResultsSeparatedPanel("separatedResults", segmentedWordListModel)
				.setOutputMarkupPlaceholderTag(true)
				.add(new VisibleModelBehavior(new SupplierModel<>(() -> GlossFormat.TOP_BOTTOM.equals(formatModel.getObject())))));
		
	}

}
