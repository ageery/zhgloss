package com.zhgloss.web.wicket.theme;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchCssReference;

public class ZhGlossTheme extends Theme {

    public ZhGlossTheme() {
        super("zhgloss", new BootswatchCssReference("cerulean"), ZhGlossLessThemeReference.instance());
    }

}
