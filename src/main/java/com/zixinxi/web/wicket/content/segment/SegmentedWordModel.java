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
import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.service.WordService;

public class SegmentedWordModel extends LoadableDetachableModel<List<SegmentedWord>>{

	@SpringBean
	private WordService service;
	
	private IModel<String> textModel;
	private IModel<CharacterType> charTypeModel;
	private IModel<TranscriptionSystemInfo> transcriptionSystemModel;
	private int maxLen;
	
	public SegmentedWordModel(IModel<String> textModel, IModel<CharacterType> charTypeModel, IModel<TranscriptionSystemInfo> transcriptionSystemModel, int maxLen) {
		super();
		this.textModel = textModel;
		this.charTypeModel = charTypeModel;
		this.transcriptionSystemModel = transcriptionSystemModel;
		this.maxLen = maxLen;
		Injector.get().inject(this);
	}
	
	@Override
	protected List<SegmentedWord> load() {
		String text = textModel.getObject();
		return StringUtils.isEmpty(text) ? Collections.emptyList() : 
			service.segmentText(text, charTypeModel.getObject(), transcriptionSystemModel.getObject().getCode(), maxLen)
				.collect(Collectors.toList());
	}

}
