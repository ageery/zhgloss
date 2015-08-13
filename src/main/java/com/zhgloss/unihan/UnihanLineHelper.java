package com.zhgloss.unihan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jooq.lambda.Unchecked;
import org.zeroturnaround.zip.ZipUtil;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zhgloss.domain.ZhGlossConstants;

public final class UnihanLineHelper {

	public static Stream<UnihanInfo> unihanLines(UnihanOneFileUnloaderConfig config) throws IOException {
		File file = Paths.get(config.getPath()).toFile();
		Files.createParentDirs(file);
		try (InputStream is = Resources.asByteSource(new URL(config.getUrl())).openBufferedStream();) {
			boolean success = ZipUtil.unpackEntry(is, config.getFilename(), file);
			if (! success) {
				throw new IOException("Unable to extract file.");
			}
			Reader reader = Files.newReader(file, ZhGlossConstants.UTF8);
			XMLStreamReader r = XMLInputFactory.newInstance().createXMLStreamReader(reader);
			return UnihanXmlCharElementParser.newParser(r, new UnihanInfoFunction())
					.stream()
					.onClose(Unchecked.runnable(() -> reader.close()));
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
	
}
