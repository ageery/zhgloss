package com.zixinxi.web.wicket.content.dictionary;

import static org.jooq.lambda.Seq.seq;
import static org.jooq.lambda.Seq.zipWithIndex;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import com.zixinxi.domain.CharacterInfo;
import com.zixinxi.domain.WordDetailSearchCriteria;

public class WordDetailSearchCriteriaModel extends LoadableDetachableModel<WordDetailSearchCriteria> {

	private static final Pattern TRANSCRIPTION_PATTERN = Pattern.compile("^(\\w+?)(\\d)?$");
	private static final String WILDCARD_CHARACTER = "*";
	private static final String WILDCARD_REGEX = "\\" + WILDCARD_CHARACTER;
	
	private IModel<WordLookupCriteria> wordLookupCriteriaModel;
	
	public WordDetailSearchCriteriaModel(IModel<WordLookupCriteria> wordLookupCriteriaModel) {
		this.wordLookupCriteriaModel = wordLookupCriteriaModel;
	}
	
	@Override
	protected WordDetailSearchCriteria load() {
		WordDetailSearchCriteria wordDetailCriteria = new WordDetailSearchCriteria();
		WordLookupCriteria wordLookupCriteria = wordLookupCriteriaModel.getObject();
		
		String def = wordLookupCriteria.getDefinition();
		if (!StringUtils.isEmpty(wordLookupCriteria.getDefinition())) {
			wordDetailCriteria.setDefinitionCriteria(Arrays.asList(def.split("\\s+")));
		}
		
		wordDetailCriteria.setCharacterInfoCriteria(
				seq(handleChars(wordLookupCriteria.getSimplifiedCharacters(), (ci, v) -> ci.setSimplified(v)))
					.concat(handleChars(wordLookupCriteria.getTraditionalCharacters(), (ci, v) -> ci.setTraditional(v)))
				 	.concat(handleTranscription(wordLookupCriteria.getPinyin()))
				.collect(Collectors.toList()));
		
		wordDetailCriteria.setWordLength(handleWordLength(wordLookupCriteria));
		
		return wordDetailCriteria;
	}
	
	private Integer handleWordLength(WordLookupCriteria wordLookupCriteria) {
		Integer charLength = handleCharacterWordLength(wordLookupCriteria);
		Integer transcriptionLength = handleTranscriptionWordLength(wordLookupCriteria);
		Integer max = null;
		if (charLength != null && transcriptionLength != null) {
			max = Integer.max(charLength, transcriptionLength);
			max = max == 0 ? null : max;
		}
		return max;
	}
	
	private Integer handleCharacterWordLength(WordLookupCriteria wordLookupCriteria) {
		Tuple2<Boolean, Integer> tuple = Stream.of(wordLookupCriteria.getSimplifiedCharacters(), wordLookupCriteria.getTraditionalCharacters())
			.map(s -> Tuple.tuple(StringUtils.contains(s, "*"), StringUtils.length(s)))
			.reduce((t1, t2) -> Tuple.tuple(t1.v1() || t2.v1(), Integer.max(t1.v2(), t2.v2())))
			.get();
		return tuple.v1() ? null : tuple.v2();	
	}
	
	private Integer handleTranscriptionWordLength(WordLookupCriteria wordLookupCriteria) {
		String pinyin = wordLookupCriteria.getPinyin();
		if (StringUtils.contains(pinyin, WILDCARD_CHARACTER)) {
			return null;
		} else {
			if (StringUtils.isEmpty(pinyin)) {
				return 0;
			} else {
				int len = pinyin.split("\\w+").length;
				return len == 0 ? 1 : len;
			}
		} 
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		wordLookupCriteriaModel.detach();
	}
	
	private Stream<CharacterInfo> handleChars(String queryValue, BiConsumer<CharacterInfo, String> setter) {
		return StringUtils.isEmpty(queryValue) ? 
				Stream.empty() : 
					zipWithIndex(Stream.of(queryValue.split(WILDCARD_REGEX)))
							.flatMap(t -> handleChars(t.v1(), setter, t.v2().equals(0L)));
	}
	
	private Stream<CharacterInfo> handleChars(String queryValue, BiConsumer<CharacterInfo, String> setter, boolean includeIndex) {
		return Seq.zipWithIndex(queryValue.codePoints().boxed())
				.map(t -> {
					CharacterInfo ci = new CharacterInfo()
						.withIndex(includeIndex ? (t.v2().intValue() + 1) : null);
					setter.accept(ci, new String(Character.toChars(t.v1())).trim());
					return ci;
				});
	}
	
	private Stream<CharacterInfo> handleTranscription(String queryValue) {
		return StringUtils.isEmpty(queryValue) ? 
				Stream.empty() : 
					zipWithIndex(Stream.of(queryValue.split(WILDCARD_REGEX)))
							.flatMap(t -> handleTranscription(t.v1(), t.v2().equals(0L)));
	}
	
	private Stream<CharacterInfo> handleTranscription(String queryValue, boolean includeIndex) {
		return StringUtils.isEmpty(queryValue) ? Stream.empty() : 
			Seq.zipWithIndex(Stream.of(queryValue.split("\\s+"))
				.filter(s -> !StringUtils.isEmpty(s)))
				.map(t -> {
					String syllable = t.v1().trim();
					Integer tone = null;
					Matcher m = TRANSCRIPTION_PATTERN.matcher(t.v1());
					if (m.matches()) {
						syllable = m.group(1);
						String ts = m.group(2);
						if (!StringUtils.isEmpty(ts)) {
							tone = Integer.parseInt(ts);
						}
					}
					return new CharacterInfo()
						.withPinyin(syllable)
						.withTone(tone)
						.withIndex(includeIndex ? (t.v2().intValue() + 1) : null);
				});
	}

}
