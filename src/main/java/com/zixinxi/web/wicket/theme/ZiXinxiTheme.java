package com.zixinxi.web.wicket.theme;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchCssReference;

public class ZiXinxiTheme extends Theme {

    public ZiXinxiTheme() {
        super("zixinxi", new BootswatchCssReference("cerulean"), ZiXinxiLessThemeReference.instance());
    }

}
