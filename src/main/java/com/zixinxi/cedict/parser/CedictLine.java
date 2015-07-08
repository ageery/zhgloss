package com.zixinxi.cedict.parser;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

public class CedictLine {

    public static final Predicate<CedictLine> IS_WORD = c -> CedictLine.Type.WORD.equals(c.getType());

    public enum Type {
        WORD,
        COMMENT,
        ERROR;
    }

    private final String line;
    private final Type type;
    private final String comment;
    private final String traditional;
    private final String simplified;
    private final String pinyin;
    private final List<String> definitions;

    private CedictLine(final String line,
                       final Type type, final String comment, final String traditional,
                       final String simplified, final String pinyin,
                       final List<String> definitions) {
        super();
        this.type = type;
        this.line = line;
        this.comment = comment;
        this.traditional = traditional;
        this.simplified = simplified;
        this.pinyin = pinyin;
        this.definitions = definitions;
    }

    public String getLine() {
        return line;
    }

    public Type getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }

    public String getTraditional() {
        return traditional;
    }

    public String getSimplified() {
        return simplified;
    }

    public String getPinyin() {
        return pinyin;
    }

    public Stream<String> getDefinitions() {
        return definitions == null ? Stream.empty() : definitions.stream();
    }

    @Override
    public String toString() {
        final ToStringHelper helper = MoreObjects.toStringHelper(this)
                .add("type", getType()) //
                .add("line", getLine());
        switch (getType()) {
            case COMMENT:
                helper.add("comment", getComment()); //
                break;
            case ERROR:
                break;
            case WORD:
                helper.add("traditional characters", getTraditional()) //
                        .add("simplified characters", getSimplified()) //
                        .add("pinyin", getPinyin()) //
                        .add("definitions", getDefinitions());
        }
        return helper.toString();
    }

    public static CedictLine error(final String line) {
        return new CedictLine(line, Type.ERROR, null, null, null,
                null, null);
    }

    public static CedictLine comment(final String line,
                                     final String comment) {
        return new CedictLine(line, Type.COMMENT, comment, null,
                null, null, null);
    }

    public static CedictLine word(final String line,
                                  final String traditional, final String simplified,
                                  final String pinyin, final List<String> definitions) {
        return new CedictLine(line, Type.WORD, null, traditional,
                simplified, pinyin, definitions);
    }

    public boolean isWord() {
        return Type.WORD.equals(type);
    }

}
