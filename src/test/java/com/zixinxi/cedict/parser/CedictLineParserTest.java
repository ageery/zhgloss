package com.zixinxi.cedict.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.lambda.Seq;
import org.junit.Before;
import org.junit.Test;

import com.zixinxi.cedict.parser.CedictLine.Type;

public class CedictLineParserTest {

	private CedictLineParser parser;
	
	@Before
	public void init() {
		parser = new CedictLineParser();
	}
	
	@Test
	public void testInvalidLine() {
		String s = "abcdef";
		CedictLine line = parser.apply(s);
		assertEquals(Type.ERROR, line.getType());
		assertEquals(s, line.getLine());
	}

	@Test
	public void testBlankLine() {
		String s = "";
		CedictLine line = parser.apply(s);
		assertEquals(Type.ERROR, line.getType());
		assertEquals(s, line.getLine());
	}

	@Test
	public void testCommentLine() {
		String comment = "abcdef";
		String s = "# " + comment;
		CedictLine line = parser.apply(s);
		assertEquals(Type.COMMENT, line.getType());
		assertEquals(s, line.getLine());
		assertEquals(comment, line.getComment());
	}
	
	@Test
	public void testCommentLineWithLeadingWhitespace() {
		String comment = "abcdef";
		String s = " # " + comment;
		CedictLine line = parser.apply(s);
		assertEquals(Type.COMMENT, line.getType());
		assertEquals(s, line.getLine());
		assertEquals(comment, line.getComment());
	}
	
	@Test
	public void testValidLine() {
		String traditional = "A";
		String simplified = "a";
		String pinyin = "b";
		List<String> defs = Arrays.asList("x", "y", "z");
		String s = String.format("%s %s [%s] %s", traditional, simplified, pinyin, defs.stream().collect(Collectors.joining("/", "/", "/")));
		CedictLine line = parser.apply(s);
		assertEquals(Type.WORD, line.getType());
		assertEquals(s, line.getLine());
		assertEquals(traditional, line.getTraditional());
		assertEquals(simplified, line.getSimplified());
		assertEquals(pinyin, line.getPinyin());
		Seq.zip(defs.stream(), line.getDefinitions()).forEach(t -> assertEquals(t.v1(), t.v2()));
	}
	
}
