package com.zixinxi.web.wicket.content.lookup;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.zixinxi.web.wicket.component.BasePage;

@MountPath("/lookup")
public class LookupPage extends BasePage {

	private static final String PARAM_NAME_PINYIN = "p";
	private static final String PARAM_NAME_SYSTEM_CODE = "s";
	
	public LookupPage(PageParameters parameters) {
		super(parameters);
		
		String pinyin = parameters.get(PARAM_NAME_PINYIN).toString();
		String systemCode = parameters.get(PARAM_NAME_SYSTEM_CODE).toString("H");
		
		WordLookupCriteria criteria = new WordLookupCriteria().withPinyin(pinyin);
		add(new LookupPanel("panel", Model.of(criteria), new TranscriptionSystemInfoModel(systemCode)));
	}
	
	public static PageParameters getPageParameters(String code, String pinyin) {
		return new PageParameters()
				.add(PARAM_NAME_SYSTEM_CODE, code)
				.add(PARAM_NAME_PINYIN, pinyin);
	}

}
