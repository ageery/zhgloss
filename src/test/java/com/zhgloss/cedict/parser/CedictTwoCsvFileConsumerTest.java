package com.zhgloss.cedict.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import com.zhgloss.cedict.parser.CedictLine;

public class CedictTwoCsvFileConsumerTest {

	private static final String EOL = "\r\n";
	
//	@Test
//	public void testLines() throws IOException {
//		StringWriter wordWriter = new StringWriter();
//		StringWriter defWriter = new StringWriter();
//		List<CedictLine> lines = newCedictLineStream().collect(Collectors.toList());
//
//		/*
//		 * Generate the csv contents.
//		 */
//		try(CedictTwoCsvFileConsumer x = new CedictTwoCsvFileConsumer(wordWriter, defWriter, () -> UUID.randomUUID().toString(), () -> UUID.randomUUID().toString());) {
//			lines.stream().forEach(x);
//		}
//		
//		/*
//		 * Word file lines. 
//		 */
//		Stream<String> actualWordFileLines = Stream.of(wordWriter.toString().split(EOL));
//		Stream<String> expectedWordFileLines = Seq.zipWithIndex(lines.stream()).map(t -> newWordLine(t.v1(), t.v2().intValue() + 1));		
//		Seq.zip(expectedWordFileLines, actualWordFileLines).forEach(t -> Assert.assertEquals(t.v1(), t.v2()));
//
//		/*
//		 * Definition file lines.
//		 */
//		Stream<String> actualDefFileLines = Stream.of(defWriter.toString().split(EOL));
//		Stream<String> expectedDefFileLines = Seq.zipWithIndex(lines.stream())
//				.map(t -> Tuple.tuple(t.v2().intValue(), t.v1().getDefinitions()))
//				.flatMap(t -> Seq.zipWithIndex(t.v2())
//					.map(s -> Tuple.tuple((t.v1() * 3) + s.v2().intValue() + 1, t.v1() + 1, s.v2().intValue() + 1, s.v1()))
//					.map(s -> newDefLine(s.v1(), s.v2(), s.v3(), s.v4())));
//		Seq.zip(expectedDefFileLines, actualDefFileLines).forEach(t -> Assert.assertEquals(t.v1(), t.v2()));
//	}
	
	private Stream<CedictLine> newCedictLineStream() {
		Builder<CedictLine> builder = Stream.builder();
		for (int i = 1; i < 10; i++) {
			String traditional = "A" + i;
			String simplified = "a" + i;
			String pinyin = "b" + i;
			int currentIndex = i;
			List<String> defs = Arrays.asList("x", "y", "z").stream().map(s -> s + currentIndex).collect(Collectors.toList());
			String s = String.format("%s %s [%s] %s", traditional, simplified, pinyin, defs.stream().collect(Collectors.joining("/", "/", "/")));
			CedictLine line = CedictLine.word(s, traditional, simplified, pinyin, defs);
			builder.add(line);
		}
		return builder.build();
	}

	private String newDefLine(int defId, int wordId, int order, String def) {
		return String.format("%d,%d,%d,%s", defId, wordId, order, def);
	}
	
	private String newWordLine(CedictLine line, int id) {
		return String.format("%d,%s,%s,%s", id, line.getTraditional(), line.getSimplified(), line.getPinyin());
	}
	
}
