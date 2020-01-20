package com.tzj.minitriangle.ast;

public class VarDeclaration extends Declaration {
  Identifier I;
  TypeDenoter T;

  public VarDeclaration(final Identifier I, final TypeDenoter T) {
    this.I = I;
    this.T = T;
  }
}
