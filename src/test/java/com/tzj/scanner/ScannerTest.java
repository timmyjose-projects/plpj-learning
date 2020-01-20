package com.tzj.scanner;

import org.junit.Test;

public class ScannerTest {
  @Test
  public void basicScannerTest() {
    String filename = "samples/basic.triangle";
    final Scanner scanner = new Scanner(filename);

    while (scanner.hasMoreTokens()) {
      System.out.println(scanner.nextToken());
    }
  }
}
