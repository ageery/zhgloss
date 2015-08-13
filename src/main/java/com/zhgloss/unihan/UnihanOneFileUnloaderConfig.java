package com.zhgloss.unihan;



public class UnihanOneFileUnloaderConfig {
	
	public static final String DEFAULT_URL = "http://www.unicode.org/Public/UCD/latest/ucdxml/ucd.unihan.flat.zip";
	public static final String DEFAULT_FILENAME = "ucd.unihan.flat.xml";
	
	private String url;
	private String path;
	private String filename;
	
	public UnihanOneFileUnloaderConfig() {
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

	public UnihanOneFileUnloaderConfig path(String path) {
		this.path = path;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UnihanOneFileUnloaderConfig url(String url) {
		this.url = url;
		return this;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public UnihanOneFileUnloaderConfig filename(String filename) {
		this.filename = filename;
		return this;
	}

}
