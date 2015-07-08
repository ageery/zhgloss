package com.zixinxi.web.wicket.content.segment;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zixinxi.domain.CharacterType;
import com.zixinxi.domain.external.SegmentedWord;
import com.zixinxi.service.WordService;

public class SegmentedWordModel extends LoadableDetachableModel<List<SegmentedWord>>{

	@SpringBean
	private WordService service;
	
	private IModel<String> textModel;
	
	public SegmentedWordModel(IModel<String> textModel) {
		super();
		this.textModel = textModel;
		Injector.get().inject(this);
	}
	
	@Override
	protected List<SegmentedWord> load() {
		String text = textModel.getObject();
		return StringUtils.isEmpty(text) ? Collections.emptyList() : 
			service.segmentText(text, CharacterType.SIMPLFIED, "H", 10)
				.collect(Collectors.toList());
	}

}
