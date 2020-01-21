package com.tzj.minitriangle.parser;

import org.junit.Test;

import com.tzj.minitriangle.ast.AST;
import com.tzj.minitriangle.ast.Program;
import com.tzj.minitriangle.errors.ParserException;

public class ParserTest {
  @Test
  public void basicParserTest() {
    final String filename = "samples/basic.triangle";
    final Parser parser = new Parser(filename);

    final AST ast = parser.parse();
    System.out.println(ast);
  }

  @Test
  public void intermediateParserTest() {
    final String filename = "samples/intermediate.triangle";
    final Parser parser = new Parser(filename);

    final AST ast = parser.parse();
    System.out.println(ast);
  }
}
