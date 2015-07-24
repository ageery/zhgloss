package com.zhgloss.jooq;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Context;
import org.jooq.Field;
import org.jooq.impl.CustomCondition;
import org.jooq.impl.DSL;

public class FullTextSearchCondition extends CustomCondition {

	private Field<Object> field;
	private String value;
	
	public FullTextSearchCondition(Field<Object> field, String... values) {
		super();
		this.field = field;
		this.value = Stream.of(values).collect(Collectors.joining(" & "));
	}
	
    @Override
    public final void accept(Context<?> ctx) {
        ctx.visit(field).sql(" @@ ").visit(DSL.sql("to_tsquery(?)", value));
    }
	
}
