package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.model.SupplierModel;

public class GlossResultsPanel extends Panel {

	public GlossResultsPanel(String id, IModel<SegmentedWordSearchCriteria> model, IModel<GlossFormat> formatModel) {
		super(id, model);
		
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
