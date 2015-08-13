package com.zhgloss.unihan;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.xml.stream.XMLStreamReader;

public class UnihanXmlCharElementParser<T> {

	private static final String XML_ELEMENT_NAME = "char";
	
	private XMLStreamReader xmlStremReader;
	private Function<XMLStreamReader, T> generator;
	
	public static <T> UnihanXmlCharElementParser<T> newParser(XMLStreamReader xmlStremReader, Function<XMLStreamReader, T> generator) {
		return new UnihanXmlCharElementParser<>(xmlStremReader, generator);
	}
	
	private UnihanXmlCharElementParser(XMLStreamReader xmlStremReader, Function<XMLStreamReader, T> generator) {
		this.xmlStremReader = xmlStremReader;
		this.generator = generator;
	}
	
	public Stream<T> stream() {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new XMLStreamReaderIterator(xmlStremReader), Spliterator.ORDERED), false)
				.filter(r -> r.getEventType() == XMLStreamReader.START_ELEMENT)
				.filter(r -> XML_ELEMENT_NAME.equals(r.getLocalName()))
				.map(generator);
	}

}
