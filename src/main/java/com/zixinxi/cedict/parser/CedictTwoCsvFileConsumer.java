package com.zixinxi.cedict.parser;

import static org.supercsv.prefs.CsvPreference.STANDARD_PREFERENCE;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.jooq.lambda.Seq;
import org.jooq.lambda.Unchecked;
import org.supercsv.io.CsvListWriter;

public class CedictTwoCsvFileConsumer implements Consumer<CedictLine>, Closeable {

	private Supplier<String> wordPkGenerator;
	private Supplier<String> defPkGenerator;
	private CsvListWriter wordWriter;
	private CsvListWriter defWriter;
	
	public CedictTwoCsvFileConsumer(Writer wordWriter, Writer defWriter, Supplier<String> wordPkGenerator, Supplier<String> defPkGenerator) {
		this.wordWriter = new CsvListWriter(wordWriter, STANDARD_PREFERENCE);
		this.defWriter = new CsvListWriter(defWriter, STANDARD_PREFERENCE);
		this.wordPkGenerator = wordPkGenerator;
		this.defPkGenerator = defPkGenerator;
	}
	
	@Override
	public void accept(CedictLine line) {
		try {
			String nextWordId = wordPkGenerator.get();
			wordWriter.write(nextWordId, line.getTraditional(), line.getSimplified(), line.getPinyin());
			Seq.zipWithIndex(line.getDefinitions()).forEach(Unchecked.consumer(t -> 
				defWriter.write(defPkGenerator.get(), nextWordId, Integer.toString(t.v2().intValue() + 1), t.v1())));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		wordWriter.close();
		defWriter.close();
	}

}
