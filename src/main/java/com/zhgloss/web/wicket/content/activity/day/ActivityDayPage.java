package com.zhgloss.web.wicket.content.activity.day;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.home.ActivityPanel;
import com.zhgloss.web.wicket.model.SupplierModel;

@MountPath("/activity/day/${" + ActivityDayPage.PARAM_NAME_DATE + "}")
public class ActivityDayPage extends TitledPage {

	static final String PARAM_NAME_DATE = "DATE";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityDayPage.class);
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	private IModel<LocalDate> dateModel;
	
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
		add(new ActivityPanel("words", Model.of(new WordDetailSearchCriteria().withCreatedDate(createdDate)),
				Model.of("H"), 100));
	}

	@Override
	protected IModel<?> getDescriptionModel() {
		return new SupplierModel<>(() -> String.format("CEDICT words loaded on %s", dateModel.getObject().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}
	
	public static PageParameters newPageParameters(LocalDate date) {
		return new PageParameters().add(PARAM_NAME_DATE, date.format(FORMATTER));
	}
	
}
