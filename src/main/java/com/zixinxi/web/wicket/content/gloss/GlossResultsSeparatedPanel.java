package com.zixinxi.web.wicket.content.gloss;

import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.wicketstuff.minis.behavior.EnabledModelBehavior;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.web.wicket.component.BootstrapExternalLink;
import com.zixinxi.web.wicket.component.ListView;
import com.zixinxi.web.wicket.component.button.EditButton;
import com.zixinxi.web.wicket.model.LambdaModel;
import com.zixinxi.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

public class GlossResultsSeparatedPanel extends Panel {
	
	public GlossResultsSeparatedPanel(String id, IModel<List<SegmentedWord>> model) {
		super(id, model);

		Form<List<SegmentedWord>> form = new Form<>("form", model);
		add(form);
		
		form.add(new Label("textHeader", Model.of("Text")));
		form.add(new EditButton("edit", new ResourceModel("label.edit")));
		form.add(new ListView<>("text", model,
				item -> { 
					BootstrapExternalLink link = new BootstrapExternalLink("word", Model.of("#def_link_" + item.getIndex()), new LambdaModel<>(item.getModel(), new OpFunction<>(SegmentedWord.FUNCTION_TEXT)));
					link.add(new CssClassNameAppender("internal-anchor"));
					link.add(new EnabledModelBehavior(new SupplierModel<>(() -> item.getModelObject().hasDefinition())));
					link.setMarkupId("word_link_" + item.getIndex());
					item.add(link);
				}));
		
		form.add(new Heading("defsHeader", Model.of("Definitions")));
		form.add(new ListView<>("defs", model, 
				item -> item.add(new WordPartsListPanel("def", item.getModel(), new SupplierModel<>(() -> "#word_link_" + item.getIndex())))
						.setMarkupId("def_link_" + item.getIndex())
						.add(new CssClassNameAppender("internal-anchor"))
						.add(new VisibleModelBehavior(new SupplierModel<>(() -> item.getModelObject().hasDefinition())))));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(new JavaScriptContentHeaderItem("window.addEventListener('hashchange', function() { scrollBy(0, -50) })", "hashchange", null));
	}

}
