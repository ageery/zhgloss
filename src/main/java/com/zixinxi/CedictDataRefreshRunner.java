package com.zixinxi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.zixinxi.service.WordService;

public class CedictDataRefreshRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CedictDataRefreshRunner.class);

	private WordService wordService;

	public CedictDataRefreshRunner(WordService wordService) {
		super();
		this.wordService = wordService;
	}

	@Override
	public void run(String... args) {
		LOGGER.info("Refreshing CEDICT data at startup...");
		int count = wordService.loadNewCedictWords();
		LOGGER.info("Finished refreshing CEDICT data at startup: count={}", count);
	}

}
