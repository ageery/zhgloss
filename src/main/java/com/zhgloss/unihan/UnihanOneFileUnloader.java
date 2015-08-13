package com.zhgloss.unihan;

import static com.zhgloss.domain.ZhGlossConstants.UTF8;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;


public class UnihanOneFileUnloader {

	private static final UnihanOneFileUnloader INSTANCE = new UnihanOneFileUnloader();
	
	private UnihanOneFileUnloader() {
		super();
	}
	
	public static UnihanOneFileUnloader get() {
		return INSTANCE;
	}
	
	public void writeFile(UnihanLineHelperConfig config) {
		try (
			Writer writer = Files.newBufferedWriter(config.getUnloadPath(), UTF8);
			UnihanPgOneFileConsumer consumer = new UnihanPgOneFileConsumer(writer, config.getFieldSeparator(), config.getQuote(), config.getEol());) 
		{
			UnihanLineHelper.unihanLines(new UnihanOneFileUnloaderConfig().filename("test"))
				.forEach(new UnihanPgOneFileConsumer(writer, config.getFieldSeparator(), config.getQuote(), config.getEol()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
