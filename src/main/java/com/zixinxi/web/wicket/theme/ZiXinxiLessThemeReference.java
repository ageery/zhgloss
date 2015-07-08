package com.zixinxi.web.wicket.theme;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.less.LessResourceReference;

public class ZiXinxiLessThemeReference extends LessResourceReference {

	public ZiXinxiLessThemeReference() {
		super(ZiXinxiLessThemeReference.class, "less/theme.less");
	}

    private static final class Holder {
        private static final ZiXinxiLessThemeReference INSTANCE = new ZiXinxiLessThemeReference();
    }

    public static ZiXinxiLessThemeReference instance() {
        return Holder.INSTANCE;
    }

	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
		dependencies.add(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
		return dependencies;
	}
	
}
