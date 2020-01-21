package com.tzj.minitriangle.ast;

public class Program extends AST {
  Command C;

  public Program(final Command C) {
    this.C = C;
  }

  @Override
  public String toString() {
    return "Program { C = " + C + " }";
  }
}
