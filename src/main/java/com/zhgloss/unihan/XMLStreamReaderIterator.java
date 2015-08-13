package com.zhgloss.unihan;

import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLStreamReaderIterator implements Iterator<XMLStreamReader> {
	
	private XMLStreamReader xmlStreamReader;
	
	public XMLStreamReaderIterator(XMLStreamReader xmlStreamReader) {
		this.xmlStreamReader = xmlStreamReader;
	}

	@Override
	public boolean hasNext() {
		try {
			return xmlStreamReader.hasNext();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XMLStreamReader next() {
		try {
			xmlStreamReader.next();
			return xmlStreamReader;
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}