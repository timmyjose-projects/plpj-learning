package com.tzj.minitriangle.ast;

public class BinaryExpression extends Expression {
  Expression E1;
  Operator O;
  Expression E2;

  public BinaryExpression(final Expression E1, final Operator O, final Expression E2) {
    this.E1 = E1;
    this.O = O;
    this.E2 = E2;
  }

  @Override
  public String toString() {
    return "BinaryExpression { E1 = " + E1 + ", O = " + O + ", E2 = " + E2 + " }";
  }
}
