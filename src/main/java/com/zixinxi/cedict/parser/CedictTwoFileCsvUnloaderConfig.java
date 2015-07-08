package com.zixinxi.cedict.parser;

import java.nio.file.Path;
import java.util.function.Supplier;

public class CedictTwoFileCsvUnloaderConfig {

	private Path cedictPath;
	private Path wordsPath;
	private Path definitionsPath;
	private Supplier<String> wordPkGenerator = new IntegerPkGenerator(1);
	private Supplier<String> defPkGenerator = new IntegerPkGenerator(1);
	private String umlautRepresentation;
	
	public CedictTwoFileCsvUnloaderConfig() {
		super();
	}

	public Path getCedictPath() {
		return cedictPath;
	}

	public void setCedictPath(Path cedictPath) {
		this.cedictPath = cedictPath;
	}

	public CedictTwoFileCsvUnloaderConfig cedictPath(Path cedictPath) {
		this.cedictPath = cedictPath;
		return this;
	}
	
	public Path getWordsPath() {
		return wordsPath;
	}

	public void setWordsPath(Path wordsPath) {
		this.wordsPath = wordsPath;
	}

	public CedictTwoFileCsvUnloaderConfig wordsPath(Path wordsPath) {
		this.wordsPath = wordsPath;
		return this;
	}
	
	public Path getDefinitionsPath() {
		return definitionsPath;
	}

	public void setDefinitionsPath(Path definitionsPath) {
		this.definitionsPath = definitionsPath;
	}

	public CedictTwoFileCsvUnloaderConfig definitionsPath(Path definitionsPath) {
		this.definitionsPath = definitionsPath;
		return this;
	}
	
	public Supplier<String> getWordPkGenerator() {
		return wordPkGenerator;
	}

	public void setWordPkGenerator(Supplier<String> wordPkGenerator) {
		this.wordPkGenerator = wordPkGenerator;
	}

	public CedictTwoFileCsvUnloaderConfig wordPkGenerator(Supplier<String> wordPkGenerator) {
		this.wordPkGenerator = wordPkGenerator;
		return this;
	}
	
	public Supplier<String> getDefPkGenerator() {
		return defPkGenerator;
	}

	public void setDefPkGenerator(Supplier<String> defPkGenerator) {
		this.defPkGenerator = defPkGenerator;
	}

	public CedictTwoFileCsvUnloaderConfig defPkGenerator(Supplier<String> defPkGenerator) {
		this.defPkGenerator = defPkGenerator;
		return this;
	}
	
	public String getUmlautRepresentation() {
		return umlautRepresentation;
	}

	public void setUmlautRepresentation(String umlautRepresentation) {
		this.umlautRepresentation = umlautRepresentation;
	}
	
	public CedictTwoFileCsvUnloaderConfig umlautRepresentation(String umlautRepresentation) {
		this.umlautRepresentation = umlautRepresentation;
		return this;
	}

	private static class IntegerPkGenerator implements Supplier<String> {

		private int nextPk;
		
		public IntegerPkGenerator(int nextPk) {
			this.nextPk = nextPk;
		}
		
		@Override
		public String get() {
			return Integer.toString(nextPk++);
		}
		
	}
	
}
