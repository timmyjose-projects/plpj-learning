package com.tzj.minitriangle.ast;

public class UnaryExpression extends Expression {
  Operator O;
  Expression E;

  public UnaryExpression(final Operator O, final Expression E) {
    this.O = O;
    this.E = E;
  }
}
