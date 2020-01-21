package com.tzj.minitriangle.ast;

public class WhileCommand extends Command {
  Expression E;
  Command C;

  public WhileCommand(final Expression E, final Command C) {
    this.E = E;
    this.C = C;
  }

  @Override
  public String toString() {
    return "WhileComamnd { E = " + E + ", C = " + C + " }";
  }
}
