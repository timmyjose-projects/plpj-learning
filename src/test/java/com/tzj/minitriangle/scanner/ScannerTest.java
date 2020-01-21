package com.tzj.minitriangle.scanner;

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

  @Test
  public void intermediateScannerTest() {
    String filename = "samples/intermediate.triangle";
    final Scanner scanner = new Scanner(filename);

    while (scanner.hasMoreTokens()) {
      System.out.println(scanner.nextToken());
    }
  }
}
