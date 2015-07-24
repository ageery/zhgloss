package com.zhgloss.web.wicket.content.dictionary;

import java.util.Iterator;
import java.util.stream.Stream;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.domain.external.TranscriptionSystemInfo;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.service.WordService;

public class WordLookupDataSource extends SortableDataProvider<WordParts, WordSorts> implements IFilterStateLocator<WordLookupCriteria> {

	@SpringBean
	private WordService service;
	
	private IModel<WordLookupCriteria> wordLookupCriteriaModel;
	private IModel<WordDetailSearchCriteria> wordDetailCriteriaModel;
	private IModel<TranscriptionSystemInfo> transcriptionSystemModel;
	private IModel<Integer> countModel;
	
	public WordLookupDataSource(IModel<WordLookupCriteria> wordLookupCriteriaModel, IModel<TranscriptionSystemInfo> transcriptionSystemModel) {
		this.wordLookupCriteriaModel = wordLookupCriteriaModel;
		this.wordDetailCriteriaModel = new WordDetailSearchCriteriaModel(wordLookupCriteriaModel);
		this.transcriptionSystemModel = transcriptionSystemModel;
		this.countModel = new WordDetailSearchCriteriaCountModel(wordDetailCriteriaModel);
		setSort(WordSorts.TRANSCRIPTION, SortOrder.ASCENDING);
		Injector.get().inject(this);
	}
	
	@Override
	public Iterator<? extends WordParts> iterator(long first, long count) {
		return service.find(
					wordDetailCriteriaModel.getObject(), 
					transcriptionSystemModel.getObject().getCode(), 
					getSorts(), 
					(int) first, 
					(int) count)
				.iterator();
	}

	@Override
	public long size() {
		return wordLookupCriteriaModel.getObject().isEmpty() ? 0L : countModel.getObject();
	}

	@Override
	public IModel<WordParts> model(WordParts wordParts) {
		return new WordPartsModel(wordParts, transcriptionSystemModel);
	}
	
	private Stream<SortInfo<WordDetailSort>> getSorts() {
		SortParam<WordSorts> sortParam = getSort();
		boolean ascending = sortParam.isAscending();
		return sortParam.getProperty().getSorts().stream().map(sort -> new SortInfo<>(sort, ascending));
	}

	@Override
	public WordLookupCriteria getFilterState() {
		return wordLookupCriteriaModel.getObject();
	}

	@Override
	public void setFilterState(WordLookupCriteria state) {
		wordLookupCriteriaModel.setObject(state);
		criteriaChanged();
	}

	public void criteriaChanged() {
		wordDetailCriteriaModel.detach();
		countModel.detach();
	}
	
}
