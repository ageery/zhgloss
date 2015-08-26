package com.zhgloss.web.wicket.content.activity.list;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;
import org.wicketstuff.minis.behavior.VisibleModelBehavior;

import com.zhgloss.domain.OptionalFunction;
import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.external.CedictLoadInfo;
import com.zhgloss.service.WordService;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.component.HeaderPanel;
import com.zhgloss.web.wicket.component.ListView;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.component.link.DetailLink;
import com.zhgloss.web.wicket.content.home.ActivityPanel;
import com.zhgloss.web.wicket.event.DetailEvent;
import com.zhgloss.web.wicket.event.SaveEvent;
import com.zhgloss.web.wicket.model.LambdaModel;
import com.zhgloss.web.wicket.model.SupplierModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;

@MountPath("/activity/list/#{" + ActivityListPage.PARAM_NAME_DATE + "}")
public class ActivityListPage extends TitledPage {

	static final String PARAM_NAME_DATE = "DATE";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListPage.class);
	private static final DateTimeFormatter PAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final DateTimeFormatter LABEL_DATE_FORMATTER = DateTimeFormatter.ofPattern("eee, yyyy-MM-dd");
	
	@SpringBean
	private WordService wordService;

	private IModel<LocalDate> activeDateModel;
	
	private Component activityPanel;
	private WebMarkupContainer tableContainer;
	
	public ActivityListPage(PageParameters parameters) {
		super();
		
		LocalDate activeDate = LocalDate.now();
		String dateString = parameters.get(PARAM_NAME_DATE).toOptionalString();
		if (dateString != null) {
			try {
				activeDate = LocalDate.parse(dateString, PAGE_DATE_FORMATTER);
			} catch (DateTimeParseException e) {
				LOGGER.warn("Unable to parse date '{}' -- defaulting to today's date", dateString);
			}
		}
		
		activeDateModel = Model.of(activeDate);
		tableContainer = new WebMarkupContainer("container");
		tableContainer.setOutputMarkupId(true);
		tableContainer.add(
				new ListView<>("loads", 
				new SupplierModel<>(() -> wordService.getCedictLoadHistory(0, 10).collect(Collectors.toList())), 
				item -> {
					IModel<LocalDateTime> dateModel = new LambdaModel<>(item.getModel(), new OptionalFunction<>(CedictLoadInfo.FUNCTION_START));
					item.add(new CssClassNameAppender(new SupplierModel<>(() -> dateModel.getObject().toLocalDate().equals(activeDateModel.getObject()) ? "active" : "")));
					item.add(new DetailLink<>("link", dateModel, 
							new SupplierModel<>(() -> dateModel.getObject().format(LABEL_DATE_FORMATTER))));
					item.add(new Label("count", new LambdaModel<>(item.getModel(), new OptionalFunction<>(CedictLoadInfo.FUNCTION_COUNT))));
				}));
		add(tableContainer);
		
		activityPanel = new HeaderPanel("details", 
						new SupplierModel<String>(() -> String.format("Words for %s", activeDateModel.getObject().format(LABEL_DATE_FORMATTER))))
					.add(new ActivityPanel("activity", new SupplierModel<>(() -> new WordDetailSearchCriteria().withCreatedDate(activeDateModel.getObject())),
							new SupplierModel<>(() -> ZhGlossSession.get().getUserSettings().getTranscriptionSystem()), 100))
					.add(new VisibleModelBehavior(new SupplierModel<>(() -> activeDateModel.getObject() != null)))
					.setOutputMarkupPlaceholderTag(true);
		add(activityPanel);
	}
	
	@OnEvent(types = LocalDateTime.class)
	public void handleDetailEvent(DetailEvent<LocalDateTime> event) {
		activeDateModel.setObject(event.getPayload().toLocalDate());
		event.getTarget().add(activityPanel, tableContainer);
	}
	
	@OnEvent(types = UserSettings.class)
	public void handleUserSettingsSaveEvent(SaveEvent<UserSettings> event) {
		event.getTarget().add(activityPanel);
	}
	
}
