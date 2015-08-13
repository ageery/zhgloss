package com.zhgloss.web.wicket.component.form;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;

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

	@Override
	public T getObject(String id, IModel<? extends List<? extends T>> choices) {
		return Seq.zipWithIndex(choices.getObject().stream())
			.map(t -> Tuple.tuple(t.v1(), idFunction.apply(t.v1(), t.v2().intValue())))
			.filter(t -> id.equals(t.v2()))
			.map(t -> t.v1())
			.findAny()
			.orElse(null);
	}

}
