package com.zhgloss.unihan;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UnihanPgOneFileConsumer implements Consumer<UnihanInfo>, Closeable {

	private Writer writer;
	private String fieldSeparator;
	private String quote;
	private String eol;
	
	public UnihanPgOneFileConsumer(Writer writer, String fieldSeparator, String quote, String eol) {
		super();
		this.writer = writer;
		this.fieldSeparator = fieldSeparator;
		this.quote = quote;
		this.eol = eol;
	}
	
	@Override
	public void accept(UnihanInfo unihanInfo) {
		String s = unihanInfo.getProperties().entrySet().stream()
				.map(e -> String.format("\"%s\" : \"%s\"", e.getKey(), e.getValue()))
				.collect(Collectors.joining(", ", "{", "}"));
		try {
			writer.write(String.format("%s%s%s%s%s%s", unihanInfo.getCharacter(), fieldSeparator, quote, s, quote, eol));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() throws IOException {
		writer.close();
	}

}
