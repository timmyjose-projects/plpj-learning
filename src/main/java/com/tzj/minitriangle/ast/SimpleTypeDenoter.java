package com.tzj.minitriangle.ast;

public class SimpleTypeDenoter extends TypeDenoter {
  Identifier I;

  public SimpleTypeDenoter(final Identifier I) {
    this.I = I;
  }

  @Override
  public String toString() {
    return "SimpleTypeDenoter { I = " + I + " }";
  }
}
