package com.zhgloss.web;

import java.beans.PropertyEditorSupport;

public class QueryOperationPropertyEditor extends PropertyEditorSupport {

	@Override
	public Object getValue() {
		return QueryOperation.findByUiValue(getSource().toString());
	}

}
