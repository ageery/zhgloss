package com.zhgloss.web.wicket.content.about;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

public class ForkMeLink extends Panel {
	
	public enum Location {
		
		UPPER_LEFT("left"),
		UPPER_RIGHT("right"),
		LOWER_LEFT("left-bottom"),
		LOWER_RIGHT("right-bottom");
		
		private String cssClass;
		
		Location(String cssClass) {
			this.cssClass = cssClass;
		}

		public String getCssClass() {
			return cssClass;
		}
		
	}
	
	public ForkMeLink(String id, IModel<String> urlModel) {
		this(id, urlModel, Location.UPPER_RIGHT);
	}

	public ForkMeLink(String id, IModel<String> urlModel, Location location) {
		super(id, urlModel);
		WebMarkupContainer container = new WebMarkupContainer("container");
		container.add(new CssClassNameAppender(location.getCssClass()));
		add(container);
		container.add(new ExternalLink("link", urlModel, getLabelModel()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssReferenceHeaderItem.forReference(new UrlResourceReference(Url.parse("//cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.1.1/gh-fork-ribbon.min.css"))));
	}
	
	protected IModel<String> getLabelModel() {
		return new ResourceModel("label.fork_me_on_github", Model.of("Fork me on GitHub"));
	}
	
}
