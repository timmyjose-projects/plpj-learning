package com.tzj.scanner;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tzj.errors.TokenizerException;

public class Tokenizer {
  private List<Char> characters;
  private int index = 0;

  public Tokenizer(final String filename) {
    this.characters = new ArrayList<>();

    String line = "";
    int linum = 0, column = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      while ((line = reader.readLine()) != null) {
        linum++;

        for (char c : line.toCharArray()) {
          column++;
          characters.add(new Char(filename, linum, column, c));
        }
        column++;
        characters.add(new Char(filename, linum, column, '\n'));
        column = 0;
      } 

      // insert EOT to mark end of stream
      characters.add(new Char(filename, -1, -1, '\000'));
    } catch (FileNotFoundException ex) {
      System.err.println("Source file not found: " + ex.getLocalizedMessage());
      System.exit(-1);
    } catch (IOException ex) {
      System.err.println("IO error: " + ex.getLocalizedMessage());
      System.exit(-1);
    }
  }

  public boolean hasMoreChars() {
    return index < characters.size();
  }

  public Char nextChar() throws TokenizerException {
    final Char c = characters.get(index);
    index++;

    return c;
  }
}

class Char {
  String filename;
  int line;
  int column;
  char c;

  public Char(String filename, int line, int column, char c) {
    this.filename = filename;
    this.line = line;
    this.column = column;
    this.c = c;
  }

  @Override
  public String toString() {
    return Character.valueOf(c).toString();
  }
}
