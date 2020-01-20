package com.tzj.minitriangle.ast;

public class SequentialCommand extends Command {
  Command C1;
  Command C2;

  public SequentialCommand(final Command C1, final Command C2) {
    this.C1 = C1;
    this.C2 = C2;
  }
}
