package com.tzj.minitriangle.ast;

public class Identifier extends Terminal {
  public Identifier(final String spelling) {
    super(spelling);
  }

  @Override
  public String toString() {
    return "Identifier { spelling = " + spelling + " }";
  }
}
