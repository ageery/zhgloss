package com.zixinxi.cedict.parser;

import static java.nio.file.Files.lines;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

import org.zeroturnaround.zip.ZipUtil;

import com.google.common.io.Files;
import com.google.common.io.Resources;

public final class CedictLineHelper {

	private static final Charset FILE_ENCODING = Charset.forName("utf8");

	public static Stream<CedictLine> cedictLines(CedictLineHelperConfig config) throws IOException {
		Function<String, CedictLine> parser = new CedictLineParser(config.getUmlautRepresentation());
		Path path = Paths.get(config.getPath());
		File file = path.toFile();
		if (!file.exists()) {
			Files.createParentDirs(file);
			try (InputStream is = Resources.asByteSource(new URL(config.getUrl())).openBufferedStream();) {
				boolean success = ZipUtil.unpackEntry(is, config.getFilename(), file);
				if (! success) {
					throw new IOException("Unable to copy file.");
				}
			}
		}
		return lines(path, FILE_ENCODING).map(parser);
	}
	
}
