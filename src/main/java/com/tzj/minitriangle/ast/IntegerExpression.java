package com.tzj.minitriangle.ast;

public class IntegerExpression extends Expression {
  IntegerLiteral I;

  public IntegerExpression(final IntegerLiteral I) {
    this.I = I;
  }
}
