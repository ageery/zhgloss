package com.zhgloss.web.wicket.theme;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.less.LessResourceReference;

public class ZhGlossLessThemeReference extends LessResourceReference {

	public ZhGlossLessThemeReference() {
		super(ZhGlossLessThemeReference.class, "less/theme.less");
	}

    private static final class Holder {
        private static final ZhGlossLessThemeReference INSTANCE = new ZhGlossLessThemeReference();
    }

    public static ZhGlossLessThemeReference instance() {
        return Holder.INSTANCE;
    }

	@Override
	public List<HeaderItem> getDependencies() {
		final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
		dependencies.add(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
		return dependencies;
	}
	
}
