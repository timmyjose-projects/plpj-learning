package com.tzj.scanner;

import java.util.List;
import java.util.ArrayList;

import com.tzj.errors.ScannerException;

public class Scanner {
  private Tokenizer tokenizer;
  private Char currentChar;
  private StringBuffer currentSpelling;

  private List<Token> tokens;
  private int index = 0;

  public Scanner(String filename) {
    this.tokenizer = new Tokenizer(filename);
    this.tokens = new ArrayList<>();
    this.scan();
  }

  // scanning starts here
  private void scan() {
    while (tokenizer.hasMoreChars()) {
      currentChar = tokenizer.nextChar();

      while (currentChar.c == '!' || currentChar.c == ' ' || currentChar.c == '\n') {
        scanSeparator();
      }

      currentSpelling = new StringBuffer();
      final Token.Kind currentKind = scanToken();

      tokens.add(new Token(currentSpelling.toString(), currentKind));
    }
  }

  private Token.Kind scanToken() {
    switch (currentChar.c) {
      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 'r':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
        {
          takeIt();

          while (isLetter(currentChar) || isDigit(currentChar)) {
            takeIt();
          }

          return Token.Kind.IDENTIFIER;
        }

      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9': 
        {
          takeIt();

          while (isDigit(currentChar)) {
            takeIt();
          }

          return Token.Kind.INTEGERLITERAL;
        }

      case '+':
      case '-':
      case '*':
      case '/':
      case '<':
      case '>':
      case '=':
      case '\\': 
        {
          takeIt();
          return Token.Kind.OPERATOR;
        }

      case ';': 
        {
          takeIt();
          return Token.Kind.SEMICOLON;
        }

      case ':': 
        {
          takeIt();

          if (currentChar.c == '=') {
            takeIt();
            return Token.Kind.BECOMES;
          }

          return Token.Kind.COLON;
        }

      case '~':
        {
          takeIt();
          return Token.Kind.IS;
        }

      case '(':
        {
          takeIt();
          return Token.Kind.LPAREN;
        }

      case ')':
        {
          takeIt();
          return Token.Kind.RPAREN;
        }

      case '\000':
        {
          return Token.Kind.EOT;
        }

      default:
        throw new ScannerException(String.format("[File: %s, Line: %d, Column: %d] could not match character '%c' with any rule", 
              currentChar.filename,
              currentChar.line,
              currentChar.column,
              currentChar.c));
    }
  }

  private void scanSeparator() {
    switch (currentChar.c) {
      case '!' : 
        {
          skipIt();

          while (isGraphic(currentChar)) {
            skipIt();
          }
          skip('\n');
        }
        break;

      case ' ' : case '\n': skipIt(); break;
    }
  }

  private boolean isLetter(final Char c) {
    return Character.isLetterOrDigit(c.c);
  }

  private boolean isDigit(final Char c) {
    return Character.isDigit(c.c);
  }

  private boolean isGraphic(final Char c) {
    switch (c.c) {
      case '\n': return false;
      default: return true;
    }
  }

  private void skipIt() {
    currentChar = tokenizer.nextChar();
  }

  private void skip(final char expectedChar) {
    if (currentChar.c !=  expectedChar) {
      throw new ScannerException(String.format("[File: %s, Line %d, column %d] expected to skip; character %c, found character %c", 
            currentChar.line,
            currentChar.column, 
            expectedChar, currentChar));
    }
  }

  private void takeIt() {
    currentSpelling.append(currentChar.c);
    currentChar = tokenizer.nextChar();
  }

  private void take(final char expectedChar) {
    if (currentChar.c != expectedChar) {
      throw new ScannerException(String.format("[File: %s, Line %d, column %d] expected character %c, found character %c", 
            currentChar.line,
            currentChar.column, 
            expectedChar, currentChar));
    }

    currentSpelling.append(currentChar.c);
    currentChar = tokenizer.nextChar();
  }

  public boolean hasMoreTokens() {
    return index < tokens.size();
  }

  public Token nextToken() {
    final Token tt = tokens.get(index);
    index++;

    return tt;
  }
}


