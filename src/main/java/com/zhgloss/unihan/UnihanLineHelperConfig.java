package com.zhgloss.unihan;

import java.nio.file.Path;

public class UnihanLineHelperConfig {

	private Path unihanXmlPath;
	private Path unloadPath;
	private String fieldSeparator;
	private String quote;
	private String eol;
	
	public UnihanLineHelperConfig() {
		super();
	}

	public Path getUnihanXmlPath() {
		return unihanXmlPath;
	}

	public void setUnihanXmlPath(Path unihanXmlPath) {
		this.unihanXmlPath = unihanXmlPath;
	}
	
	public UnihanLineHelperConfig withUnihanXmlPath(Path unihanXmlPath) {
		setUnihanXmlPath(unihanXmlPath);
		return this;
	}

	public Path getUnloadPath() {
		return unloadPath;
	}

	public void setUnloadPath(Path unloadPath) {
		this.unloadPath = unloadPath;
	}
	
	public UnihanLineHelperConfig withUnloadPath(Path unloadPath) {
		setUnloadPath(unloadPath);
		return this;
	}

	public String getFieldSeparator() {
		return fieldSeparator;
	}

	public void setFieldSeparator(String fieldSeparator) {
		this.fieldSeparator = fieldSeparator;
	}
	
	public UnihanLineHelperConfig withFieldSeparator(String fieldSeparator) {
		setFieldSeparator(fieldSeparator);
		return this;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public UnihanLineHelperConfig withQuote(String quote) {
		setQuote(quote);
		return this;
	}

	public String getEol() {
		return eol;
	}

	public void setEol(String eol) {
		this.eol = eol;
	}
	
	public UnihanLineHelperConfig withEol(String eol) {
		setEol(eol);
		return this;
	}
	
}
