package com.tzj.minitriangle.ast;

public class AssignCommand extends Command {
  Vname V;
  Expression E;

  public AssignCommand(final Vname V, final Expression E) {
    this.V = V;
    this.E = E;
  }
}
