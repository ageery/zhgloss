package com.zhgloss.web.wicket.content.home;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.component.HeaderPanel;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.model.SupplierModel;

public class ActivityPanel extends Panel {

	@SpringBean
	private WordService wordService;
	
	public ActivityPanel(String id) {
		super(id);
		
		HeaderPanel activityPanel = new HeaderPanel("panel", cid -> new ActivityHeaderPanel(cid));
		add(activityPanel);
		
		activityPanel.add(new Label("title", Model.of("Recently Added Words")));
		
		activityPanel.add(new ListView<>("words", 
				new SupplierModel<>(() -> wordService.find(new WordDetailSearchCriteria(), "H", Stream.of(new SortInfo<>(WordDetailSort.CREATED, false)), 0, 20).collect(Collectors.toList())), 
				item -> item.add(new WordSummaryPanel("word", item.getModel()))));
	}

}
