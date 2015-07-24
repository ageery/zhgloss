package com.zhgloss.cedict.parser;

import static com.zhgloss.domain.ZhGlossConstants.UTF8;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.util.stream.Stream;

public class CedictTwoFileCsvUnloader {
	
	private static final CedictTwoFileCsvUnloader INSTANCE = new CedictTwoFileCsvUnloader();
	
	private CedictTwoFileCsvUnloader() {
		super();
	}
	
	public static CedictTwoFileCsvUnloader get() {
		return INSTANCE;
	}
	
	public void writeFiles(CedictTwoFileCsvUnloaderConfig config) throws Exception {
		try (BufferedWriter words = Files.newBufferedWriter(config.getWordsPath(), UTF8);
			 BufferedWriter defs = Files.newBufferedWriter(config.getDefinitionsPath(), UTF8);
			 CedictTwoCsvFileConsumer consumer = new CedictTwoCsvFileConsumer(words, defs, 
					 config.getWordPkGenerator(), config.getDefPkGenerator());
			Stream<CedictLine> cedictLines = CedictLineHelper.cedictLines(new CedictLineHelperConfig().path(config.getCedictPath().toString()))) 
		{
			cedictLines
				.filter(CedictLine::isWord)
				.forEach(consumer);
		}
	}
	
}
