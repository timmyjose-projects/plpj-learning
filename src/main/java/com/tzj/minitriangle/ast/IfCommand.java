package com.tzj.minitriangle.ast;

public class IfCommand extends Command {
  Identifier I;
  Expression E;

  public IfCommand(final Identifier I, final Expression E) {
    this.I = I;
    this.E = E;
  }
}
