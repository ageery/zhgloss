package com.zixinxi.repo;

import static com.zixinxi.repo.jooq.tables.FindSegmentsDetails.FIND_SEGMENTS_DETAILS;
import static com.zixinxi.repo.jooq.tables.Word.WORD;
import static com.zixinxi.repo.jooq.tables.WordDef.WORD_DEF;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.RecordMapper;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.jooq.lambda.Seq;
import org.postgresql.util.PGobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.zixinxi.domain.CharacterInfo;
import com.zixinxi.domain.CharacterTranscription;
import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.SegmentDetail;
import com.zixinxi.domain.SortInfo;
import com.zixinxi.domain.WordDetail;
import com.zixinxi.domain.WordDetailSearchCriteria;
import com.zixinxi.domain.WordDetailSort;
import com.zixinxi.domain.WordDetails;
import com.zixinxi.domain.WordInfo;
import com.zixinxi.jooq.FullTextSearchCondition;
import com.zixinxi.jooq.WordInfoCondition;
import com.zixinxi.repo.jooq.Routines;
import com.zixinxi.repo.jooq.tables.CedictLoad;
import com.zixinxi.repo.jooq.tables.records.CedictLoadRecord;

public class WordRepoImpl implements WordRepo {
	
	private static final String WINDOW_TABLE_NAME = "x";
	private static final String DEFINITION_ARRAY_COLUMN_NAME = "def_arr";
	private static final String WORD_ID_COLUMN_NAME = "id";
	private static final String WORD_INFO_COLUMN_NAME = "word_info";
	private static final Field<String> EMPTY_STRING_FIELD = DSL.field("''", String.class);
	
	private DSLContext context;
	private ObjectMapper mapper;
	
	public WordRepoImpl(DSLContext context, ObjectMapper mapper) {
		super();
		this.context = context;
		this.mapper = mapper;
	}
	
	@Override
	public int updateWordsFromCedictData() {
		CedictLoadRecord clr = context.newRecord(CedictLoad.CEDICT_LOAD);
		clr.setLoadStart(LocalDateTime.now());
		int count = Routines.updateWordsFromCedictWords(context.configuration());
		clr.setLoadFinish(LocalDateTime.now());
		clr.setLoadCount(count);
		context.execute("vacuum analyze");
		clr.store();
		return count;
	}
	
	@Override
	public Stream<SegmentDetail> segmentText(String text, CharacterType type, String transcriptionSystemCode, int maxLen) {
		return context
			.select(FIND_SEGMENTS_DETAILS.WORD_DETAILS, FIND_SEGMENTS_DETAILS.CHARS)
			.from(Routines.findSegmentsDetails(text, type.getDbValue(), transcriptionSystemCode, maxLen, true))
			.orderBy(FIND_SEGMENTS_DETAILS.ORDER_NUM)
			.fetch(new SegmentDetailMapper())
			.stream();
	}
	
	@Override
	public int count(WordDetailSearchCriteria criteria) {
		List<Condition> conditions = Seq.of(
				getWordLengthCondition(criteria), 
				getCharacterCondition(criteria), 
				getDefinitionCondition(criteria))
			.flatMap(op -> op.isPresent() ? Stream.of(op.get()) : Stream.empty())
			.collect(Collectors.toList());
		return context
				.with(WINDOW_TABLE_NAME).as(context
					.select(
							WORD.ID, 
							WORD.WORD_INFO)
					.from(WORD
					.join(WORD_DEF, JoinType.JOIN).on(WORD.ID.eq(WORD_DEF.WORD_ID)))
					.where(conditions)
					.groupBy(WORD.ID, WORD.WORD_INFO))
				.select(DSL.count())
				.from(DSL.table(WINDOW_TABLE_NAME))
				.fetchOne(0, int.class);
	}
	
	@Override
	public Stream<WordDetail> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit) {		
		List<Condition> conditions = Seq.of(
				getIdCondition(criteria),
				getWordLengthCondition(criteria), 
				getCharacterCondition(criteria), 
				getDefinitionCondition(criteria))
			.flatMap(op -> op.isPresent() ? Stream.of(op.get()) : Stream.empty())
			.collect(Collectors.toList());
		return context
			.with(WINDOW_TABLE_NAME).as(context
				.select(
						WORD.ID, 
						WORD.WORD_INFO, 
						DSL.arrayAgg(WORD_DEF.DEF).orderBy(WORD_DEF.ORDER_NUM).as(DEFINITION_ARRAY_COLUMN_NAME))
				.from(WORD
				.join(WORD_DEF, JoinType.JOIN).on(WORD.ID.eq(WORD_DEF.WORD_ID)))
				.where(conditions)
				.groupBy(WORD.ID, WORD.WORD_INFO))
			.with("y").as(context
				.select(
					DSL.field(DSL.name(WINDOW_TABLE_NAME, WORD_ID_COLUMN_NAME), UUID.class).as("id"),
					Routines.jsonbArrayAttrValuesToString(DSL.field(DSL.name(WINDOW_TABLE_NAME, WORD_INFO_COLUMN_NAME), WordInfo.class), DSL.field("'trad'", String.class), EMPTY_STRING_FIELD).as("trad_chars"),
					Routines.jsonbArrayAttrValuesToString(DSL.field(DSL.name(WINDOW_TABLE_NAME, WORD_INFO_COLUMN_NAME), WordInfo.class), DSL.field("'simp'", String.class), EMPTY_STRING_FIELD).as("simp_chars"),
					Routines.makeTranscriptionJson(DSL.field(DSL.name(WINDOW_TABLE_NAME, WORD_INFO_COLUMN_NAME), WordInfo.class), DSL.field(String.format("'%s'", transcriptionSystemCode), String.class)).as("transcription"),
					DSL.field(DSL.name(WINDOW_TABLE_NAME, DEFINITION_ARRAY_COLUMN_NAME), String[].class).as("defs"))
				.from(DSL.table(WINDOW_TABLE_NAME)))
			.select(
					DSL.field("id", UUID.class),
					DSL.field("trad_chars", String.class),
					DSL.field("simp_chars", String.class),
					DSL.field("transcription", Object.class),
					DSL.field("defs", String[].class)
					)
			.from(DSL.table("y"))
			.orderBy(getOrderBy(sorts).collect(Collectors.toList()))
			//.orderBy(DSL.field("jsonb_array_attr_values_to_string(transcription::jsonb, 'toned-syllable', ' ')")) //, DSL.field("transcription->>'tone'"))
			//.orderBy(DSL.field(DSL.name(WINDOW_TABLE_NAME, "word_info", "->>", "'pinyin'"), String.class))
			.limit(limit)
			.offset(offset)
			.fetch(new WordDetailMapper())
			.stream();
	}
	
	private static final Map<WordDetailSort, List<Field<?>>> SORT_MAP = ImmutableMap.<WordDetailSort, List<Field<?>>>builder()
			.put(WordDetailSort.TRADITIONAL, Arrays.asList(DSL.field("trad_chars")))
			.put(WordDetailSort.SIMPLIFIED, Arrays.asList(DSL.field("simp_chars")))
			.put(WordDetailSort.TRANSCRIPTION, Arrays.asList(DSL.field("jsonb_array_attr_values_to_string(transcription::jsonb, 'toned-syllable', ' ')")))
			.build();
	
	private Stream<SortField<?>> getOrderBy(Stream<SortInfo<WordDetailSort>> sorts) {
		return sorts.flatMap(si -> Seq.zip(
					SORT_MAP.get(si.getProperty()).stream(), 
					Seq.generate(() -> si.isAscending())))
				.map(t -> t.v1().sort(t.v2() ? SortOrder.ASC : SortOrder.DESC));
	}
	
	private static Optional<Condition> getCharacterCondition(WordDetailSearchCriteria criteria) {
		List<CharacterInfo> list = criteria.getCharacterInfoCriteria();
		return (list != null) && (!list.isEmpty()) ? 
				Optional.of(new WordInfoCondition(WORD.WORD_INFO, new WordInfo(list))) :
				Optional.empty();
	}
	
	private static Optional<Condition> getDefinitionCondition(WordDetailSearchCriteria criteria) {
		List<String> list = criteria.getDefinitionCriteria();
		return (list != null) && (!list.isEmpty()) ? 
				Optional.of(new FullTextSearchCondition(WORD_DEF.DEF_TSV, toFtsQueryString(list.stream()))) : 
				Optional.empty(); 
	}

	private static Optional<Condition> getIdCondition(WordDetailSearchCriteria criteria) {
		UUID id = criteria.getId();
		return id != null ? Optional.of(WORD.ID.eq(id)) : Optional.empty();
	}
	
	private static Optional<Condition> getWordLengthCondition(WordDetailSearchCriteria criteria) {
		Integer wordLength = criteria.getWordLength();
		return wordLength != null ? Optional.of(DSL.condition("jsonb_array_length(word_info) = ?", wordLength)) : Optional.empty();
	}
	
	private static String toFtsQueryString(Stream<String> termStream) {
		return termStream.collect(Collectors.joining(" & ", "\"", "\""));
	}
	
	private class WordDetailMapper implements RecordMapper<Record5<UUID, String, String, Object, String[]>, WordDetail> {

		@Override
		public WordDetail map(Record5<UUID, String, String, Object, String[]> r) {
			String s = ((PGobject) r.value4()).getValue();
			try {
				return new WordDetail()
					.withId(r.value1())
					.withTraditionalCharacters(r.value2())
					.withSimplifiedCharacters(r.value3())
					.withCharacterTranscriptions(Arrays.asList(mapper.readValue(s, CharacterTranscription[].class)))
					.withDefinitions(Arrays.asList(r.value5()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	private class SegmentDetailMapper implements RecordMapper<Record2<WordDetails, String>, SegmentDetail> {

		@Override
		public SegmentDetail map(Record2<WordDetails, String> r) {
			WordDetails details = r.value1();
			return new SegmentDetail()
				.withText(r.value2())
				.withDetails(details == null ? Collections.emptyList() : details.getWordDetails());
		}
		
	}

}
