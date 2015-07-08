package com.zixinxi.cedict.parser;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CedictLineParser implements Function<String, CedictLine> {

    private static final String COMMENT_GROUP = "comment";
    private static final String TRADITIONAL_GROUP = "traditional";
    private static final String SIMPLIFIED_GROUP = "simplified";
    private static final String PINYIN_GROUP = "pinyin";
    private static final String DEFINITIONS_GROUP = "defs";

    private static final String DEFINITION_SEPARATOR = "/";
    private static final Pattern COMMENT_REGEX = Pattern
            .compile("^\\s*#\\s*(?<" + COMMENT_GROUP + ">.*?)\\s*$");
    private static final Pattern DEF_REGEX = Pattern.compile("^"
            + "(?<" + TRADITIONAL_GROUP + ">.+)\\s+"
            + "(?<" + SIMPLIFIED_GROUP + ">.+)\\s+"
            + "\\[(?<" + PINYIN_GROUP + ">.+)\\]\\s+"
            + "/(?<" + DEFINITIONS_GROUP + ">.+)/$");
    private static final String UMLAUT_REGEX = "u:";
    
    private String umlautRepresentation;
    
    public CedictLineParser() {
        this(null);
    }
    
    public CedictLineParser(String umlautRepresentation) {
    	super();
        this.umlautRepresentation = umlautRepresentation;
    }

    @Override
    public CedictLine apply(final String line) {
        CedictLine cedictLine = null;
        Matcher m = DEF_REGEX.matcher(line);
        if (m.matches()) {
        	String p = m.group(PINYIN_GROUP);
        	String pinyin = umlautRepresentation == null || p == null ? p
        			: p.replaceAll(UMLAUT_REGEX, umlautRepresentation);
            cedictLine = CedictLine.word(line,
                    m.group(TRADITIONAL_GROUP),
                    m.group(SIMPLIFIED_GROUP),
                    pinyin,
                    Arrays.asList(m.group(DEFINITIONS_GROUP)
                            .split(DEFINITION_SEPARATOR)));
        } else {
            m = COMMENT_REGEX.matcher(line);
            if (m.matches()) {
                cedictLine = CedictLine.comment(line, m.group(COMMENT_GROUP));
            } else {
                cedictLine = CedictLine.error(line);
            }
        }
        return cedictLine;
    }

}
