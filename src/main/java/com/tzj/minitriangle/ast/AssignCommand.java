package com.tzj.minitriangle.ast;

public class AssignCommand extends Command {
  Vname V;
  Expression E;

  public AssignCommand(final Vname V, final Expression E) {
    this.V = V;
    this.E = E;
  }

  @Override
  public String toString() {
    return "AssignCommand { V = " + V + ", E = " + E + " }";
  }
}
