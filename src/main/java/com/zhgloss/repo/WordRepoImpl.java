package com.zhgloss.repo;

import static com.zhgloss.repo.jooq.Routines.jsonbArrayAttrValuesToString;
import static com.zhgloss.repo.jooq.Routines.makeTranscriptionJson;
import static com.zhgloss.repo.jooq.Routines.updateWordsFromCedictWords;
import static com.zhgloss.repo.jooq.tables.CedictLoad.CEDICT_LOAD;
import static com.zhgloss.repo.jooq.tables.FindSegmentsDetails.FIND_SEGMENTS_DETAILS;
import static com.zhgloss.repo.jooq.tables.Word.WORD;
import static com.zhgloss.repo.jooq.tables.WordDef.WORD_DEF;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.rowNumber;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.RecordMapper;
import org.jooq.Select;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.lambda.Seq;
import org.postgresql.util.PGobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.zhgloss.domain.CharacterInfo;
import com.zhgloss.domain.CharacterTranscription;
import com.zhgloss.domain.CharacterType;
import com.zhgloss.domain.SegmentDetail;
import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetail;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.domain.WordDetails;
import com.zhgloss.domain.WordInfo;
import com.zhgloss.jooq.FullTextSearchCondition;
import com.zhgloss.jooq.WordInfoCondition;
import com.zhgloss.repo.jooq.Routines;
import com.zhgloss.repo.jooq.tables.records.CedictLoadRecord;

public class WordRepoImpl implements WordRepo {
	
	private static String NAME_ID = "id";
	private static String NAME_WORD_INFO = "word_info";
	private static String NAME_DEFS = "defs";
	private static String NAME_SORT_ORDER = "sort_order";
	
	private static final Field<String> FIELD_SIMPLIFIED = DSL.field("'simp'", String.class);
	private static final Field<String> FIELD_TRADITIONAL = DSL.field("'trad'", String.class);
	private static final Field<String> FIELD_PINYIN = DSL.field("'pinyin'", String.class);
	private static final Field<String> FIELD_EMPTY_STRING = DSL.field("''", String.class);
	private static final Field<String> FIELD_SPACE_STRING = DSL.field("' '", String.class);
	
	private static final Map<WordDetailSort, Supplier<Stream<Field<?>>>> SORT_MAP = ImmutableMap.<WordDetailSort, Supplier<Stream<Field<?>>>>builder()
			.put(WordDetailSort.TRADITIONAL, () -> Stream.of(jsonbArrayAttrValuesToString(DSL.field(DSL.name("word_info"), WordInfo.class), FIELD_TRADITIONAL, FIELD_EMPTY_STRING)))
			.put(WordDetailSort.SIMPLIFIED, () -> Stream.of(jsonbArrayAttrValuesToString(DSL.field(DSL.name("word_info"), WordInfo.class), FIELD_SIMPLIFIED, FIELD_EMPTY_STRING)))
			.put(WordDetailSort.CREATED, () -> Stream.of(DSL.field("created_date")))
			.put(WordDetailSort.TRANSCRIPTION, () -> Stream.of(jsonbArrayAttrValuesToString(DSL.field(DSL.name("word_info"), WordInfo.class), FIELD_PINYIN, FIELD_SPACE_STRING)))
			.build();
	
	private DSLContext context;
	private ObjectMapper mapper;
	
	public WordRepoImpl(DSLContext context, ObjectMapper mapper) {
		super();
		this.context = context;
		this.mapper = mapper;
	}
	
	@Override
	public int updateWordsFromCedictData() {
		CedictLoadRecord clr = context.newRecord(CEDICT_LOAD);
		clr.setLoadStart(LocalDateTime.now());
		int count = updateWordsFromCedictWords(context.configuration());
		clr.setLoadFinish(now());
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
		return context
				.select(DSL.count())
				.from(WORD)
				.where(criteriaToConditions(criteria))
				.fetchOne(0, int.class);
	}
	
	private List<Condition> criteriaToConditions(WordDetailSearchCriteria criteria) {
		return Seq.of(
				getIdCondition(criteria),
				getWordLengthCondition(criteria), 
				getCharacterCondition(criteria), 
				getDefinitionCondition(criteria))
			.flatMap(op -> op.isPresent() ? Stream.of(op.get()) : Stream.empty())
			.collect(Collectors.toList());
	}
	
	private Collection<? extends SortField<?>> sortsToOrderBy(Stream<SortInfo<WordDetailSort>> sorts) {
		return getOrderBy(sorts).collect(Collectors.toList());
	}
	
	private Select<Record2<UUID, Integer>> getWordIdAndOrder(WordDetailSearchCriteria criteria, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit) {
		return context
			.select(WORD.ID, 
					rowNumber().over().orderBy(sortsToOrderBy(sorts)))
			.from(WORD)
			.where(criteriaToConditions(criteria))
			.limit(limit)
			.offset(offset);
	}
	
	private Select<Record4<UUID, WordInfo, Integer, String[]>> getWordInfoAndDefs(Table<Record> table, Field<UUID> idField, Field<Integer> sortField) {
		return context
			.select(WORD.ID, 
					WORD.WORD_INFO,
					sortField,
					DSL.arrayAgg(WORD_DEF.DEF).orderBy(WORD_DEF.ORDER_NUM))
			.from(table)
			.join(WORD, JoinType.JOIN).on(WORD.ID.eq(idField))
			.join(WORD_DEF, JoinType.JOIN).on(WORD.ID.eq(WORD_DEF.WORD_ID))
			.groupBy(WORD.ID, WORD.WORD_INFO, sortField);
	}
	
	
	
	public Stream<WordDetail> find(WordDetailSearchCriteria criteria, String transcriptionSystemCode, Stream<SortInfo<WordDetailSort>> sorts, int offset, int limit) {
		Table<Record> criteriaAndSortQuery = DSL.table(DSL.name("w"));
		Table<Record> wordWithDefinitionsQuery = DSL.table(DSL.name("x"));
		
		return context
			.with(criteriaAndSortQuery.getName(), NAME_ID, NAME_SORT_ORDER)
				.as(getWordIdAndOrder(criteria, sorts, offset, limit))
			.with(wordWithDefinitionsQuery.getName(), NAME_ID, NAME_WORD_INFO, NAME_SORT_ORDER, NAME_DEFS)
				.as(getWordInfoAndDefs(criteriaAndSortQuery, 
						field(name(criteriaAndSortQuery.getName(), NAME_ID), UUID.class), 
						field(name(criteriaAndSortQuery.getName(), NAME_SORT_ORDER), int.class)))
			.select(field(name(wordWithDefinitionsQuery.getName(), NAME_ID), UUID.class),
					jsonbArrayAttrValuesToString(DSL.field(DSL.name(wordWithDefinitionsQuery.getName(), NAME_WORD_INFO), WordInfo.class), FIELD_TRADITIONAL, FIELD_EMPTY_STRING),
					jsonbArrayAttrValuesToString(DSL.field(DSL.name(wordWithDefinitionsQuery.getName(), NAME_WORD_INFO), WordInfo.class), FIELD_SIMPLIFIED, FIELD_EMPTY_STRING),
					makeTranscriptionJson(DSL.field(DSL.name(wordWithDefinitionsQuery.getName(), NAME_WORD_INFO), WordInfo.class), field(format("'%s'", transcriptionSystemCode), String.class)),
					field(name(wordWithDefinitionsQuery.getName(), NAME_DEFS), String[].class))
			.from(wordWithDefinitionsQuery)
			.orderBy(field(name(wordWithDefinitionsQuery.getName(), NAME_SORT_ORDER)))
			.fetch(new WordDetailMapper())
			.stream();
	}
	
	private Stream<SortField<?>> getOrderBy(Stream<SortInfo<WordDetailSort>> sorts) {
		return sorts.flatMap(si -> Seq.zip(
					SORT_MAP.get(si.getProperty()).get(), 
					Seq.generate(() -> si.isAscending())))
				.map(t -> t.v1().sort(t.v2() ? SortOrder.ASC : SortOrder.DESC));
	}
	
	private static Optional<Condition> getCharacterCondition(WordDetailSearchCriteria criteria) {
		List<CharacterInfo> list = criteria.getCharacterInfoCriteria();
		return (list != null) && (!list.isEmpty()) ? 
				Optional.of(new WordInfoCondition(WORD.WORD_INFO, new WordInfo(list))) :
				Optional.empty();
	}
	
	private Optional<Condition> getDefinitionCondition(WordDetailSearchCriteria criteria) {
		List<String> list = criteria.getDefinitionCriteria();
		return (list != null) && (!list.isEmpty()) ? 
				Optional.of(WORD.ID.in(context.select(WORD_DEF.WORD_ID).from(WORD_DEF).where(new FullTextSearchCondition(WORD_DEF.DEF_TSV, toFtsQueryString(list.stream()))))) :
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

	@Override
	public int countAll() {
		return context
				.select(DSL.count())
				.from(WORD)
				.fetchOne(0, int.class);
	}

}
