package com.tzj.minitriangle.ast;

public class LetCommand extends Command {
  Declaration D;
  Command C;

  public LetCommand(final Declaration D, final Command C) {
    this.D = D;
    this.C = C;
  }
}
