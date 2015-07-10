package com.zixinxi.web;

import static com.zixinxi.web.ZixinxiWebConstants.REST_PATH;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jooq.lambda.Seq;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zixinxi.domain.CharacterInfo;
import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.WordDetailSearchCriteria;
import com.zixinxi.domain.WordDetailSorts;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.domain.external.WordParts;
import com.zixinxi.service.WordService;

@RestController
// FIXME: map to enums
// FIXME: implement sorting
public class WordRestController {
	
	private static final String PARAM_NAME_QUERY = "q";
	private static final String PARAM_NAME_TRANSCRIPTION = "t";
	private static final String PARAM_NAME_SORT = "s";
	private static final String PARAM_NAME_SORT_DIRECTION = "d";
	private static final String PARAM_NAME_OPERATION = "o";
	private static final String PARAM_NAME_FIRST = "f";
	private static final String PARAM_NAME_COUNT = "c";
	
	@Inject
	private WordService service;
	
	public WordRestController() {
		super();
	}

	@RequestMapping(REST_PATH + "/segments/simp")
	public List<SegmentedWord> segmentSimplifiedText(			
			@RequestParam(value = PARAM_NAME_QUERY, required = true) String text, 
			@RequestParam(value = PARAM_NAME_TRANSCRIPTION, defaultValue = "H") String transcriptionSystemCode)
	{
		return service.segmentText(text, CharacterType.SIMPLFIED, transcriptionSystemCode, 8)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(REST_PATH + "/segments/trad")
	public List<SegmentedWord> segmentTraditionalText(			
			@RequestParam(value = PARAM_NAME_QUERY, required = true) String text, 
			@RequestParam(value = PARAM_NAME_TRANSCRIPTION, defaultValue = "H") String transcriptionSystemCode)
	{
		return service.segmentText(text, CharacterType.TRADITIONAL, transcriptionSystemCode, 8)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(REST_PATH + "/words/simp")
	public List<WordParts> findBySimplifiedCharacters(
			@RequestParam(value = PARAM_NAME_QUERY, required = true) String chars, 
			@RequestParam(value = PARAM_NAME_OPERATION, defaultValue = "e") String operationCode,
			@RequestParam(value = PARAM_NAME_TRANSCRIPTION, defaultValue = "H") String transcriptionSystemCode, 
			@RequestParam(value = PARAM_NAME_SORT, defaultValue = "T") String sort,
			@RequestParam(value = PARAM_NAME_SORT_DIRECTION, defaultValue = "A") String sortDirection,
			@RequestParam(value = PARAM_NAME_FIRST, defaultValue = "0") int offset,
			@RequestParam(value = PARAM_NAME_COUNT, defaultValue = "10") int count) 
	{		
		QueryOperation operation = QueryOperation.findByUiValue(operationCode);
		WordDetailSearchCriteria criteria = new WordDetailSearchCriteria()
			.withCharacterInfoCriteria(
				Seq.zipWithIndex(chars.chars()
					.mapToObj(Character::toChars)
					.map(String::valueOf))
					.map(t -> new CharacterInfo()
						.withSimplified(t.v1())
						.withIndex((QueryOperation.CONTAINS.equals(operation)) ? null : (t.v2().intValue() + 1)))
					.collect(Collectors.toList()))
			.withWordLength(QueryOperation.EXACT.equals(operation) ? chars.length() : null);
		return service.find(criteria, transcriptionSystemCode, WordDetailSorts.getSortInfo(WordDetailSorts.SIMPLIFIED, !"d".equalsIgnoreCase(sortDirection)), offset, count)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(REST_PATH + "/words/trad")
	public List<WordParts> findByTraditionalCharacters(
			@RequestParam(value = PARAM_NAME_QUERY, required = true) String chars, 
			@RequestParam(value = PARAM_NAME_OPERATION, defaultValue = "e") String operationCode,
			@RequestParam(value = PARAM_NAME_TRANSCRIPTION, defaultValue = "H") String transcriptionSystemCode, 
			@RequestParam(value = PARAM_NAME_SORT, defaultValue = "T") String sort,
			@RequestParam(value = PARAM_NAME_SORT_DIRECTION, defaultValue = "A") String sortDirection,
			@RequestParam(value = PARAM_NAME_FIRST, defaultValue = "0") int offset,
			@RequestParam(value = PARAM_NAME_COUNT, defaultValue = "10") int count) 
	{		
		QueryOperation operation = QueryOperation.findByUiValue(operationCode);
		WordDetailSearchCriteria criteria = new WordDetailSearchCriteria()
			.withCharacterInfoCriteria(
				Seq.zipWithIndex(chars.chars()
					.mapToObj(Character::toChars)
					.map(String::valueOf))
					.map(t -> new CharacterInfo()
						.withTraditional(t.v1())
						.withIndex((QueryOperation.CONTAINS.equals(operation)) ? null : (t.v2().intValue() + 1)))
					.collect(Collectors.toList()))
			.withWordLength(QueryOperation.EXACT.equals(operation) ? chars.length() : null);
		return service.find(criteria, transcriptionSystemCode, WordDetailSorts.getSortInfo(WordDetailSorts.TRADITIONAL, !"d".equalsIgnoreCase(sortDirection)), offset, count)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(REST_PATH + "/words/def")
	public List<WordParts> findByDefinition(
			@RequestParam(value = PARAM_NAME_QUERY, required = true) String terms, 
			@RequestParam(value = PARAM_NAME_TRANSCRIPTION, defaultValue = "H") String transcriptionSystemCode, 
			@RequestParam(value = PARAM_NAME_SORT, defaultValue = "H") String sort,
			@RequestParam(value = PARAM_NAME_SORT_DIRECTION, defaultValue = "A") String sortDirection,
			@RequestParam(value = PARAM_NAME_FIRST, defaultValue = "0") int offset,
			@RequestParam(value = PARAM_NAME_COUNT, defaultValue = "10") int count) 
	{		
		WordDetailSearchCriteria criteria = new WordDetailSearchCriteria()
			.withDefinitionCriteria(Arrays.asList(terms.split("\\s+")));
		return service.find(criteria, transcriptionSystemCode, WordDetailSorts.getSortInfo(WordDetailSorts.TRADITIONAL, !"d".equalsIgnoreCase(sortDirection)), offset, count)
				.collect(Collectors.toList());
	}
	
	// FIXME: this does not work
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(QueryOperation.class, new QueryOperationPropertyEditor());
	}
	
	
}
