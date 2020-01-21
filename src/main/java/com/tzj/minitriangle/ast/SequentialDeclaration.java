package com.tzj.minitriangle.ast;

public class SequentialDeclaration extends Declaration {
  Declaration D1;
  Declaration D2;

  public SequentialDeclaration(final Declaration D1, final Declaration D2) {
    this.D1 = D1;
    this.D2 = D2;
  }

  @Override
  public String toString() {
    return "SequentialDeclaration { D1 = " + D1 + ", D2 = " + D2 + " }";
  }
}
