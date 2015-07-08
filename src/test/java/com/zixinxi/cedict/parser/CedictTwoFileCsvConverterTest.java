package com.zixinxi.cedict.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.Test;

public class CedictTwoFileCsvConverterTest {

	private static final String EOL = "\r\n";
	
//	@Test
//	public void testLines() {
//		CedictTwoFileCsvConverter converter = new CedictTwoFileCsvConverter();
//		for (int i = 1; i < 10; i++) {
//			String traditional = "A" + i;
//			String simplified = "a" + i;
//			String pinyin = "b" + i;
//			int currentIndex = i;
//			List<String> defs = Arrays.asList("x", "y", "z").stream().map(s -> s + currentIndex).collect(Collectors.toList());
//			String s = String.format("%s %s [%s] %s", traditional, simplified, pinyin, defs.stream().collect(Collectors.joining("/", "/", "/")));
//			CedictLine line = CedictLine.word(s, traditional, simplified, pinyin, defs);
//			Tuple2<String, Stream<String>> t = converter.apply(line);
//			String wordLine = String.format("%d,%s,%s,%s%s", i, traditional, simplified, pinyin, EOL);
//			assertEquals(wordLine, t.v1());
//			Seq.zipWithIndex(Seq.zip(defs.stream(), t.v2())).forEach(x -> {
//				Tuple2<String, String> tuple = x.v1();
//				String expectedDef = tuple.v1();
//				String defLine = tuple.v2();
//				int order = x.v2().intValue() + 1;
//				String expectedLine = String.format("%d,%d,%d,%s%s", ((currentIndex - 1) * 3) + order, currentIndex, order, expectedDef, EOL);
//				assertEquals(expectedLine, defLine);
//			});
//		}
//	}
	
}
