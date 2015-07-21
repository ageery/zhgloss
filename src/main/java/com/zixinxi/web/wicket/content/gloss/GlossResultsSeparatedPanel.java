package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.minis.behavior.EnabledModelBehavior;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.model.LambdaModel;
import com.zixinxi.web.wicket.model.SupplierModel;

public class GlossResultsSeparatedPanel extends Panel {
	
	public GlossResultsSeparatedPanel(String id, IModel<List<SegmentedWord>> model) {
		super(id, model);
		ListView<SegmentedWord> defs = new ListView<>("defs", model, 
				item -> item.add(new WordPartsListPanel("def", /*item.getModel()))*/ new LambdaModel<>(item.getModel(), new OpFunction<>(SegmentedWord.FUNCTION_WORDS))))
						.setMarkupId("def_link_" + item.getIndex())
						.add(new VisibleModelBehavior(new SupplierModel<>(() -> item.getModelObject().hasDefinition()))));
		add(defs);
		
		add(new ListView<>("text", model,
				item -> { 
					GlossWordLink link = new GlossWordLink("word", Model.of("#def_link_" + item.getIndex()), new LambdaModel<>(item.getModel(), new OpFunction<>(SegmentedWord.FUNCTION_TEXT)));
					link.add(new EnabledModelBehavior(new SupplierModel<>(() -> item.getModelObject().hasDefinition())));
					link.setMarkupId("word_link_" + item.getIndex());
					item.add(link);
				}));
	}

}
