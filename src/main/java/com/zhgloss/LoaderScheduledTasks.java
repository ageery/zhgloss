package com.zhgloss;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhgloss.service.WordService;

@Component
public class LoaderScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoaderScheduledTasks.class);
	
	@Inject
	private WordService wordService;
	
	public LoaderScheduledTasks() {
		super();
	}
	
	/*
	 * Load new CEDICT words every day at 01:07:13 AM EST.
	 */
	@Scheduled(cron = "13 7 1 * * *", zone = "EST")
	public void loadUpdatedCedictData() {
		LOGGER.info("Starting to refresh CEDICT data from scheduler");
		int count = wordService.loadNewCedictWords();
		LOGGER.info("Finished refreshing CEDICT data from scheduler: count={}", count);
	}
	
}
