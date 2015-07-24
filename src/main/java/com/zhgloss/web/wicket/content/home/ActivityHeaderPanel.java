package com.zhgloss.web.wicket.content.home;

import java.time.format.DateTimeFormatter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.BadgeBehavior;

public class ActivityHeaderPanel extends Panel {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@SpringBean
	private WordService wordService;
	
	public ActivityHeaderPanel(String id) {
		super(id);
		add(new Label("title", Model.of("Activity")));
		add(new Label("wordCountLabel", Model.of("Words")));
		add(new Label("lastUpdateLabel", Model.of("Updated")));
		add(new Label("wordCount", new SupplierModel<>(() -> wordService.countAll()))
				.add(new BadgeBehavior()));
		add(new Label("lastUpdate", new SupplierModel<>(() -> 
					wordService.getLastCedictLoad()
						.map(time -> time.format(DATE_FORMAT))
						.orElse("N/A")))
			.add(new BadgeBehavior()));
	}

}
