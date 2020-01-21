package com.tzj.minitriangle.scanner;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Token {
  public String spelling;
  public Kind kind;
  public int line;
  public int column;
  public String filename;

  public Token(final String spelling, final Token.Kind kind, int line, int column, String filename) {
    this.spelling = spelling;

    if (Token.isKeyword(spelling)) {
      this.kind = Token.getKeywordKind(spelling);
    } else {
      this.kind = kind;
    }
    this.filename = filename;
    this.line = line;
    this.column = column;
  }

  public enum Kind {
    IDENTIFIER("<identifier>"),
    INTEGERLITERAL("<integer-literal>"),
    LET("<let>"),
    IN("<in>"),
    WHILE("<while>"),
    DO("<do>"),
    IF("<if>"),
    THEN("<then>"),
    ELSE("<else>"),
    BEGIN("<begin>"),
    END("<end>"),
    IS("<is>"),
    BECOMES("<becomes>"),
    COLON("<colon>"),
    SEMICOLON("<semi-colon>"),
    CONST("<const>"),
    VAR("<var>"),
    OPERATOR("<operator>"),
    LPAREN("("),
    RPAREN(")"),
    EOT("<eot>");

    private String description;

    private Kind(final String description) {
      this.description = description;
    }

    public String description() {
      return this.description;
    }
  }

  private static boolean isKeyword(final String spelling) {
    return keywordMap.containsKey(spelling.toLowerCase());
  }

  private static Token.Kind getKeywordKind(final String spelling) {
    return keywordMap.get(spelling.toLowerCase());
  }

  @Override
  public String toString() {
    return "<"  + this.kind.description() + ", " + this.spelling + ">";
  }

  private static Map<String, Token.Kind> keywordMap;

  static {
    keywordMap = new HashMap<>();

    keywordMap.put("let", Token.Kind.LET);
    keywordMap.put("in", Token.Kind.IN);
    keywordMap.put("while", Token.Kind.WHILE);
    keywordMap.put("do", Token.Kind.DO);
    keywordMap.put("if", Token.Kind.IF);
    keywordMap.put("then", Token.Kind.THEN);
    keywordMap.put("else", Token.Kind.ELSE);
    keywordMap.put("begin", Token.Kind.BEGIN);
    keywordMap.put("end", Token.Kind.END);
    keywordMap.put("is", Token.Kind.IS);
    keywordMap.put("becomes", Token.Kind.BECOMES);
    keywordMap.put(":", Token.Kind.COLON);
    keywordMap.put(";", Token.Kind.SEMICOLON);
    keywordMap.put("const", Token.Kind.CONST);
    keywordMap.put("var", Token.Kind.VAR);
    keywordMap.put("(", Token.Kind.LPAREN);
    keywordMap.put(")", Token.Kind.RPAREN);
    keywordMap.put("\000", Token.Kind.EOT);
  }
}
