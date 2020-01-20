package com.tzj.minitriangle.ast;

public class IfCommand extends Command {
  Expression E;
  Command C1;
  Command C2;

  public IfCommand(final Expression E, final Command C1, final Command C2) {
    this.E = E;
    this.C1 = C1;
    this.C2 = C2;
  }
}
