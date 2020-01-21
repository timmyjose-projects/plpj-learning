package com.tzj.minitriangle.ast;

public class VnameExpression extends Expression {
  Identifier I;

  public VnameExpression(final Identifier I) {
    this.I = I;
  }

  @Override
  public String toString() {
    return "VnameExpression { I = " + I + " }";
  }
}
