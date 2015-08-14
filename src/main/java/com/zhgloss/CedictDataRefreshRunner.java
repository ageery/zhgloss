package com.zhgloss;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.zhgloss.service.WordService;

public class CedictDataRefreshRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CedictDataRefreshRunner.class);

	private WordService wordService;

	public CedictDataRefreshRunner(WordService wordService) {
		super();
		this.wordService = wordService;
	}

	@Override
	public void run(String... args) {
		LocalDateTime lastLoad = wordService.getLastCedictLoad().orElse(LocalDateTime.MIN);
		if (lastLoad.isBefore(LocalDateTime.now().minusDays(1))) {
			LOGGER.info("Loading CEDICT data at startup since it is out-of-date...");
			int count = wordService.loadNewCedictWords();
			LOGGER.info("Finished refreshing CEDICT data at startup: count={}", count);
		}
	}

}
