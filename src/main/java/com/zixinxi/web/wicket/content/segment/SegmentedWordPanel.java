package com.zixinxi.web.wicket.content.segment;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

import com.zixinxi.domain.OpFunction;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.web.wicket.model.LambdaModel;

public class SegmentedWordPanel extends Panel {

	public SegmentedWordPanel(String id, IModel<SegmentedWord> model) {
		super(id, model);
		
		add(new Label("characters", new LambdaModel<>(model, new OpFunction<>(SegmentedWord.FUNCTION_TEXT))));
		add(new Label("transcription", new SegmentedWordTranscriptionsModel(model)));
		
		WebMarkupContainer definitionContainer = new WebMarkupContainer("definitionContainer");
		add(definitionContainer);
		
		definitionContainer.add(new AttributeAppender("title", new SegmentedWordDefinitionsModel(model)));
		definitionContainer.add(new Label("shortDefinition", new SegmentedFirstWordDefinitionModel(model)));
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
	
	private static class SegmentedFirstWordDefinitionModel extends LoadableDetachableDependentModel<String, SegmentedWord> {
		
		public SegmentedFirstWordDefinitionModel(IModel<SegmentedWord> dependentModel) {
			super(dependentModel);
		}

		@Override
		protected String load() {
			SegmentedWord word = getDependentModel().getObject();
			return word.getWordStream()
					.map(WordParts::getDefinitionStream)
					.flatMap(Function.identity())
					.findFirst()
					.orElse(null);
		}
	}

}
