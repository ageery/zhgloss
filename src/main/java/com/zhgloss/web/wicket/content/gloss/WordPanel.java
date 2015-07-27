package com.zhgloss.web.wicket.content.gloss;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.SegmentedWord;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.web.wicket.behavior.SameTitleAsContentAppender;
import com.zhgloss.web.wicket.model.BlankToNbspModel;
import com.zhgloss.web.wicket.model.LambdaModel;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

public class WordPanel extends Panel {

	public WordPanel(String id, IModel<SegmentedWord> model) {
		super(id, model);
		
		WebMarkupContainer container = new WebMarkupContainer("container");
		container.add(new CssClassNameAppender(new SupplierModel<>(() -> model.getObject().hasDefinition() ? "panel panel-default" : "")));
		add(container);
		
		container.add(new Label("transcriptions", new BlankToNbspModel(new SegmentedWordTranscriptionsModel(model)))
			.add(new SameTitleAsContentAppender())
			.setEscapeModelStrings(false));
		container.add(new Label("characters", new LambdaModel<>(model, new OptionalFunction<>(SegmentedWord.FUNCTION_TEXT)))
			.add(new SameTitleAsContentAppender()));
		container.add(new Label("definitions", new BlankToNbspModel(new SegmentedWordDefinitionsModel(model)))
			.add(new SameTitleAsContentAppender())
			.setEscapeModelStrings(false));
		 
	}
	
	private static class SegmentedWordTranscriptionsModel extends LoadableDetachableDependentModel<String, SegmentedWord> {

		public SegmentedWordTranscriptionsModel(IModel<SegmentedWord> dependentModel) {
			super(dependentModel);
		}

		@Override
		protected String load() {
			SegmentedWord word = getDependentModel().getObject();
			return word.getWordStream()
					.map(WordParts::getTranscription)
					.collect(Collectors.joining("; "));
		}
		
	}
	
	private static class SegmentedWordDefinitionsModel extends LoadableDetachableDependentModel<String, SegmentedWord> {

		public SegmentedWordDefinitionsModel(IModel<SegmentedWord> dependentModel) {
			super(dependentModel);
		}

		@Override
		protected String load() {
			SegmentedWord word = getDependentModel().getObject();
			return word.getWordStream()
					.map(WordParts::getDefinitionStream)
					.flatMap(Function.identity())
					.collect(Collectors.joining("; "));
		}
		
	}

}
