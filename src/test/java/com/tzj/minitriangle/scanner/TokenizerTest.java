package com.tzj.minitriangle.scanner;

import java.util.List;
import org.junit.Test;

public class TokenizerTest {
  @Test
  public void basicTokenizerTest() {
    final String filename = "samples/basic.triangle";
    final Tokenizer tokenizer = new Tokenizer(filename);

    while (tokenizer.hasMoreChars()) {
      System.out.print(tokenizer.nextChar() + " ");
    }
  }

  @Test
  public void intermediateTokenizerTest() {
    final String filename = "samples/intermediate.triangle";
    final Tokenizer tokenizer = new Tokenizer(filename);

    while (tokenizer.hasMoreChars()) {
      System.out.print(tokenizer.nextChar() + " ");
    }
  }
}
