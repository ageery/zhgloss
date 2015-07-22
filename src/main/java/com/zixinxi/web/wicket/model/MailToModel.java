package com.zixinxi.web.wicket.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

public class MailToModel extends LoadableDetachableDependentModel<String, String> {

	public MailToModel(IModel<String> dependentModel) {
		super(dependentModel);
	}

	@Override
	protected String load() {
		String emailAddress = getDependentModel().getObject();
		return StringUtils.isEmpty(emailAddress) ? null : String.format("mailto:%s", emailAddress);
	}

}
