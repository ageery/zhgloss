package com.zixinxi.web.wicket.component;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Provides the dom structure for the site-wide ajax loader mask which indicates
 * to the user that the application is making a call to the server. Initially the
 * dom fragment is hidden. The fragment is revealed to the user when a Wicket
 * ajax call is sent to the server and then the fragment is hidden once a response
 * is received by the client.
 */
public class AjaxLoaderMaskPanel extends Panel {

    public static final String DOM_ID = "ajax-loader-mask";

    public AjaxLoaderMaskPanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(false);
        add(new Image("ajaxLoaderImage", new PackageResourceReference(AjaxLoaderMaskPanel.class, "ajax-loader.gif"))
        		.add(new AttributeAppender("alt", Model.of("Loading"))));
    }

    /**
     * Add global CSS and JavaScript resources.
     *
     * @param response The response to which the resources are added to.
     */
    @Override
    public void renderHead(final org.apache.wicket.markup.head.IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new CssResourceReference(AjaxLoaderMaskPanel.class,
                "ajax-loader-mask.css")));
    }

}
