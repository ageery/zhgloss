package com.zhgloss.web.wicket.content.activity.day;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.event.annotation.OnEvent;

import com.zhgloss.domain.UserSettings;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.web.wicket.app.ZhGlossSession;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.home.ActivityPanel;
import com.zhgloss.web.wicket.event.SaveEvent;
import com.zhgloss.web.wicket.model.SupplierModel;

@MountPath("/activity/day/${" + ActivityDayPage.PARAM_NAME_DATE + "}")
public class ActivityDayPage extends TitledPage {

	static final String PARAM_NAME_DATE = "DATE";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityDayPage.class);
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	private IModel<LocalDate> dateModel;
	
	private Component activityPanel;
	
	public ActivityDayPage(PageParameters parameters) {
		super(parameters);
		
		LocalDate createdDate = LocalDate.now();
		String dateString = parameters.get(PARAM_NAME_DATE).toOptionalString();
		if (dateString != null) {
			try {
				createdDate = LocalDate.parse(dateString, FORMATTER);
			} catch (DateTimeParseException e) {
				LOGGER.warn("Unable to parse date '{}' -- defaulting to today's date", dateString);
			}
		}
		dateModel = Model.of(createdDate);
		activityPanel = new ActivityPanel("words", Model.of(new WordDetailSearchCriteria().withCreatedDate(createdDate)),
					new SupplierModel<>(() -> ZhGlossSession.get().getUserSettings().getTranscriptionSystem()), 100)
				.setOutputMarkupId(true);
		add(activityPanel);
	}

	@Override
	protected IModel<?> getDescriptionModel() {
		return new SupplierModel<>(() -> String.format("CEDICT words loaded on %s", dateModel.getObject().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}
	
	public static PageParameters newPageParameters(LocalDate date) {
		return new PageParameters().add(PARAM_NAME_DATE, date.format(FORMATTER));
	}
	
	@OnEvent(types = UserSettings.class)
	public void handleUserSettingsSaveEvent(SaveEvent<UserSettings> event) {
		event.getTarget().add(activityPanel);
	}
	
}
