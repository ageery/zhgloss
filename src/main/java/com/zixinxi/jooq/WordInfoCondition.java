package com.zixinxi.jooq;

import org.jooq.Context;
import org.jooq.Field;
import org.jooq.impl.CustomCondition;
import org.jooq.impl.DSL;

import com.zixinxi.domain.WordInfo;

public class WordInfoCondition extends CustomCondition {

	private Field<WordInfo> field;
	private WordInfo value;
	
	public WordInfoCondition(Field<WordInfo> field, WordInfo value) {
		super();
		this.field = field;
		this.value = value;
	}
	
    @Override
    public final void accept(Context<?> ctx) {
        ctx.visit(field).sql(" @> ").visit(DSL.val(value)).sql("::jsonb");
    }
	
}
