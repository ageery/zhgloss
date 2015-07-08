package com.zixinxi.cedict.parser;



public class CedictLineHelperConfig {

	public static final String DEFAULT_URL = "http://www.mdbg.net/chindict/export/cedict/cedict_1_0_ts_utf-8_mdbg.zip";
	public static final String DEFAULT_FILENAME = "cedict_ts.u8";
	
	private String url;
	private String path;
	private String filename;
	private String umlautRepresentation;
	
	public CedictLineHelperConfig() {
		super();
		this.url = DEFAULT_URL;
		this.filename = DEFAULT_FILENAME;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public CedictLineHelperConfig path(String path) {
		this.path = path;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CedictLineHelperConfig url(String url) {
		this.url = url;
		return this;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CedictLineHelperConfig filename(String filename) {
		this.filename = filename;
		return this;
	}

	public String getUmlautRepresentation() {
		return umlautRepresentation;
	}

	public void setUmlautRepresentation(String umlautRepresentation) {
		this.umlautRepresentation = umlautRepresentation;
	}
	
	public CedictLineHelperConfig umlautRepresentation(String umlautRepresentation) {
		this.umlautRepresentation = umlautRepresentation;
		return this;
	}
	
}
