package com.zhgloss;

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
		if (wordService.countAll() == 0) {
			LOGGER.info("Loading CEDICT data since there are currently no words...");
			int count = wordService.loadNewCedictWords();
			LOGGER.info("Finished refreshing CEDICT data at startup: count={}", count);
		}
	}

}
