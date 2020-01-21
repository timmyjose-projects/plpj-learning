package com.tzj.minitriangle.ast;

public class SimpleVname extends Vname {
  Identifier I;

  public SimpleVname(final Identifier I) {
    this.I = I;
  }

  @Override
  public String toString() {
    return "SimpleVname { I = " + I + " }";
  }
}
