package com.tzj.minitriangle.ast;

public class ConstDeclaration extends Declaration {
  Identifier I;
  Expression E;

  public ConstDeclaration(final Identifier I, final Expression E) {
    this.I = I;
    this.E = E;
  }

  @Override
  public String toString() {
    return "ConstDeclaration { I = " + I + ", E = " + E + " }";
  }
}
