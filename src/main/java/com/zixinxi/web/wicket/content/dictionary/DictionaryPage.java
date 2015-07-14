package com.zixinxi.web.wicket.content.dictionary;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.domain.external.TranscriptionSystemInfo;
import com.zixinxi.web.wicket.component.TitledPage;

@MountPath("/dictionary")
public class DictionaryPage extends TitledPage {

	private static final String PARAM_NAME_PINYIN = "p";
	private static final String PARAM_NAME_SYSTEM_CODE = "s";
	
	public DictionaryPage(PageParameters parameters) {
		super(parameters);
		
		String pinyin = parameters.get(PARAM_NAME_PINYIN).toString();
		String systemCode = parameters.get(PARAM_NAME_SYSTEM_CODE)
				.toString(TranscriptionSystemInfo.CODE_HANYU_PINYIN);
		
		WordLookupCriteria criteria = new WordLookupCriteria().withPinyin(pinyin);
		add(new DictionaryPanel("panel", Model.of(criteria), new TranscriptionSystemInfoModel(systemCode)));
	}
	
	public static PageParameters getPageParameters(String code, String pinyin) {
		return new PageParameters()
				.add(PARAM_NAME_SYSTEM_CODE, code)
				.add(PARAM_NAME_PINYIN, pinyin);
	}

}
