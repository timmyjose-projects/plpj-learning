package com.tzj.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FastTokenization {
  private static final String SRC_FILE = "samples/basic.triangle";

  public static void main(String[] args) {
    final List<Character> characters = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(SRC_FILE))) {
      String line;

      while ((line = reader.readLine()) != null) {
        for (char c : line.toCharArray()) {
          characters.add(c);
        }
      }
    } catch (FileNotFoundException fnex) {
      System.err.println("File not found: " + SRC_FILE);
      System.exit(1);
    } catch (IOException ex) {
      System.err.println("An IO error occurred: " + ex.getLocalizedMessage());
    }

    for (char c : characters) {
      System.out.print(c + " ");
    }
    System.out.println();
  }
}