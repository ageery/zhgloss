package com.zhgloss.web.wicket.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.IModel;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

public class BlankToNbspModel extends LoadableDetachableDependentModel<String, String> {

	public BlankToNbspModel(IModel<String> dependentModel) {
		super(dependentModel);
	}

	@Override
	protected String load() {
		String s = getDependentModel().getObject();
		return StringUtils.isBlank(s) ? "&nbsp;" : s;
	}

}
