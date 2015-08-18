package com.zhgloss.web.wicket.content.activity.list;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.minis.model.LoadableDetachableDependentModel;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.external.CedictLoadInfo;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.component.BookmarkableLink;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.activity.day.ActivityDayPage;
import com.zhgloss.web.wicket.model.LambdaModel;
import com.zhgloss.web.wicket.model.SupplierModel;

@MountPath("/activity/list")
public class ActivityListPage extends TitledPage {

	@SpringBean
	private WordService wordService;
	
	public ActivityListPage() {
		super();
		
		add(new ListView<>("loads", 
				new SupplierModel<>(() -> wordService.getCedictLoadHistory(0, 20).collect(Collectors.toList())), 
				item -> {
					item.add(new Label("start", new LoadDayModel(new LambdaModel<>(item.getModel(), new OptionalFunction<>(CedictLoadInfo.FUNCTION_START)))));
					item.add(new BookmarkableLink<>("link", ActivityDayPage.class, 
							() -> ActivityDayPage.newPageParameters(item.getModelObject().getStart().toLocalDate()))
							.add(new Label("count", new LambdaModel<>(item.getModel(), new OptionalFunction<>(CedictLoadInfo.FUNCTION_COUNT)))));
				}));
	}
	
	private static class LoadDayModel extends LoadableDetachableDependentModel<String, LocalDateTime> {

		public LoadDayModel(IModel<LocalDateTime> dependentModel) {
			super(dependentModel);
		}

		@Override
		protected String load() {
			LocalDateTime dateTime = getDependentModel().getObject();
			if (dateTime == null) {
				return null;
			}
			LocalDate date = dateTime.toLocalDate();
			String s = null;
			
			if (LocalDate.now().equals(date)) {
				s = "Today";
			} else if (LocalDate.now().minusDays(1).equals(date)) {
				s = "Yesterday";
			} else {
				s = date.format(DateTimeFormatter.ofPattern("eee, YYYY-MM-dd"));
			}
			
			return s;
		}
		
	}
	
}
