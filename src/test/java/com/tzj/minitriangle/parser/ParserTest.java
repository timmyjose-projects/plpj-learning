package com.tzj.minitriangle.parser;

import org.junit.Test;

import com.tzj.minitriangle.ast.Program;
import com.tzj.minitriangle.errors.ParserException;

public class ParserTest {
  @Test
  public void basicParserTest() {
    final String filename = "samples/basic.triangle";
    final Parser parser = new Parser(filename);

    final Program ast = parser.parse();
    System.out.println(ast);
  }
}
