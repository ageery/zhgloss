package com.zhgloss.web.wicket.component.form;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import com.zhgloss.domain.SerializableBiFunction;
import com.zhgloss.domain.SerializableFunction;

public class ChoiceRenderer<T> implements IChoiceRenderer<T> {

	private SerializableFunction<T, Object> displayFunction;
	private SerializableBiFunction<T, Integer, String> idFunction;
	
	public ChoiceRenderer(SerializableFunction<T, Object> displayFunction, SerializableBiFunction<T, Integer, String> idFunction) {
		this.displayFunction = displayFunction;
		this.idFunction = idFunction;
	}
	
	@Override
	public Object getDisplayValue(T object) {
		return displayFunction.apply(object);
	}

	@Override
	public String getIdValue(T object, int index) {
		return idFunction.apply(object, index);
	}

}
