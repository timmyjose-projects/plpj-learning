package com.tzj.minitriangle.ast;

public class CallCommand extends Command {
  Identifier I;
  Expression E;

  public CallCommand(final Identifier I, final Expression E) {
    this.I = I;
    this.E = E;
  }

  @Override
  public String toString() {
    return "CallCommand { I = " + I + ", E = " + " }";
  }
}
