package com.tzj.minitriangle.parser;

import com.tzj.minitriangle.scanner.Scanner;
import com.tzj.minitriangle.scanner.Token;
import com.tzj.minitriangle.ast.*;
import com.tzj.minitriangle.errors.ParserException;

public class Parser {
  private Token currentToken;
  private Scanner scanner;

  public Parser(final String filename) {
    this.scanner = new Scanner(filename);  
  }    

  // parsing starts here
  public Program parse() {
    this.currentToken = scanner.nextToken();
    final Program ast = parseProgram();
    accept(Token.Kind.EOT);

    return ast;
  }

  // Program ::= single-Command
  private Program parseProgram() {
    return new Program(parseSingleCommand());
  }

  // single-Command ::= AssignCommand | CallCommand | IfCommand | WhileCommand | LetCommand | BlockCommand
  private Command parseSingleCommand() {
    Command cmdAST = null;

    switch (currentToken.kind) {
      case IDENTIFIER: 
        {
          final Identifier idAST = parseIdentifier();
          switch (currentToken.kind) {
            case BECOMES: 
              {
                final Expression eAST = parseExpression();
                cmdAST = new AssignCommand(new SimpleVname(idAST), eAST);
              }
              break;

            case LPAREN: 
              {
                acceptIt();
                final Expression eAST = parseExpression();
                accept(Token.Kind.RPAREN);
                cmdAST = new CallCommand(idAST, eAST);
              }
              break;

            default: 
              {
                throw new ParserException(String.format("[File: %s, Line %d, column %d] expected token of kind %s or %s, found token of kind %s",
                      currentToken.filename,
                      currentToken.line,
                      currentToken.column, 
                      Token.Kind.BECOMES,
                      Token.Kind.LPAREN,
                      currentToken.kind));
              }
          }
        }
        break;

      case IF:
        {
          acceptIt();
          final Expression eAST = parseExpression();
          accept(Token.Kind.THEN);
          final Command cmdAST1 = parseSingleCommand();
          accept(Token.Kind.ELSE);
          final Command cmdAST2 = parseSingleCommand();

          cmdAST = new IfCommand(eAST, cmdAST1, cmdAST2);
        }
        break;

      case WHILE:
        {
          acceptIt();
          final Expression eAST = parseExpression();
          accept(Token.Kind.DO);
          final Command cmdAST1 = parseSingleCommand();

          cmdAST = new WhileCommand(eAST, cmdAST1);
        }
        break;

      case LET: 
        {
          acceptIt();
          final Declaration dAST = parseDeclaration();
          accept(Token.Kind.IN);
          final Command cmdAST1 = parseSingleCommand();

          cmdAST = new LetCommand(dAST, cmdAST1);
        }
        break;

      case BEGIN: 
        {
          acceptIt();
          final Command cmdAST1 = parseCommand();
          accept(Token.Kind.END);

          cmdAST = cmdAST1;
        }
        break;

      default:
        {
          throw new ParserException(String.format("[File: %s, Line %d, column %d] failed to parse single command. Unexpected token kind: %s",
                currentToken.filename,
                currentToken.line,
                currentToken.column, 
                currentToken.kind));
        }
    }

    return cmdAST;
  }

  private Expression parseExpression() {
    Expression eAST1 = parsePrimaryExpression();

    while (currentToken.kind == Token.Kind.OPERATOR) {
      final Operator opAST = parseOperator();
      final Expression eAST2 = parsePrimaryExpression();
      eAST1 = new BinaryExpression(eAST1, opAST, eAST2);
    }

    return eAST1;
  }

  private Expression parsePrimaryExpression() {
    Expression eAST = null;

    switch (currentToken.kind) {
      case INTEGERLITERAL: 
        {
          final IntegerLiteral intLitAST = parseIntegerLiteral();
          eAST = new IntegerExpression(intLitAST);
        } 
        break;

      case IDENTIFIER:
        {
          final Identifier idAST = parseIdentifier();
          eAST = new VnameExpression(idAST);
        }
        break;

      case OPERATOR:
        {
          final Operator opAST = parseOperator();
          final Expression eAST1 = parseExpression();

          eAST = new UnaryExpression(opAST, eAST1);
        }
        break;

      case LPAREN:
        {
          acceptIt();
          final Expression eAST1 = parseExpression();
          accept(Token.Kind.RPAREN);

          eAST = eAST1;
        }
        break;

      default: {
                 throw new ParserException(String.format("[File: %s, Line %d, column %d] failed to parse primary expression. Unexpected token kind: %s",
                       currentToken.filename,
                       currentToken.line,
                       currentToken.column, 
                       currentToken.kind));
      }
    }

    return eAST;
  }

  private Declaration parseDeclaration() {
    Declaration declAST1 = parseSingleDeclaration();

    while (currentToken.kind == Token.Kind.SEMICOLON) {
      acceptIt();
      final Declaration declAST2 = parseSingleDeclaration();
      declAST1 = new SequentialDeclaration(declAST1, declAST2);
    }

    return declAST1;
  }

  private Declaration parseSingleDeclaration() {
    Declaration declAST = null;

    switch (currentToken.kind) {
      case CONST: 
        {
          acceptIt();
          final Identifier idAST = parseIdentifier();
          accept(Token.Kind.IS);
          final Expression eAST = parseExpression();

          declAST = new ConstDeclaration(idAST, eAST);
        }
        break;

      case VAR: 
        {
          acceptIt();
          final Identifier idAST = parseIdentifier();
          accept(Token.Kind.COLON);
          final TypeDenoter tdAST = parseTypeDenoter();

          declAST = new VarDeclaration(idAST, tdAST);
        }
        break;

      default: 
        {
          throw new ParserException(String.format("[File: %s, Line %d, column %d] failed to parse single declaration. Expected %s or %s, found %s",
                currentToken.filename,
                currentToken.line,
                currentToken.column, 
                Token.Kind.CONST,
                Token.Kind.VAR,
                currentToken.kind));
        }
    }

    return declAST;
  }

  private TypeDenoter parseTypeDenoter() {
    return parseSimpleTypeDenoter();
  }

  private TypeDenoter parseSimpleTypeDenoter() {
    return new SimpleTypeDenoter(parseIdentifier());
  }

  private Command parseCommand() {
    Command cmdAST1 = parseSingleCommand();

    while (currentToken.kind == Token.Kind.SEMICOLON) {
      acceptIt();
      final Command cmdAST2 = parseSingleCommand();
      cmdAST1 = new SequentialCommand(cmdAST1, cmdAST2);
    }

    return cmdAST1;
  }

  private Identifier parseIdentifier() {
    if (currentToken.kind != Token.Kind.IDENTIFIER) {
      throw new ParserException(String.format("[File: %s, Line %d, column %d] expected an identifier, found %s", 
            currentToken.filename,
            currentToken.line,
            currentToken.column, 
            currentToken.kind));
    }

    final Identifier idAST = new Identifier(currentToken.spelling);
    currentToken = scanner.nextToken();

    return idAST;
  }

  private Operator parseOperator() {
    if (currentToken.kind != Token.Kind.OPERATOR) {
      throw new ParserException(String.format("[File: %s, Line: %d, Column: %d] expected an operator, found %s",
            currentToken.filename,
            currentToken.line,
            currentToken.column,
            currentToken.kind));
    }

    final Operator opAST = new Operator(currentToken.spelling);
    currentToken = scanner.nextToken();

    return opAST;
  }

  private IntegerLiteral parseIntegerLiteral() {
    if (currentToken.kind != Token.Kind.INTEGERLITERAL) {
      throw new ParserException(String.format("[File: %s, Line: %d, Column: %d] expected an integer literal, found %s",
            currentToken.filename,
            currentToken.line,
            currentToken.column,
            currentToken.kind));
    } 

    final IntegerLiteral intLitAST = new IntegerLiteral(currentToken.spelling);
    currentToken = scanner.nextToken();

    return intLitAST;
  }

  private void accept(final Token.Kind expectedTokenKind) {
    if (currentToken.kind != expectedTokenKind) {
      throw new ParserException(String.format("[File: %s, Line %d, column %d] expected token of kind %s; found token of kind %s", 
            currentToken.filename,
            currentToken.line,
            currentToken.column, 
            expectedTokenKind, 
            currentToken.kind));
    }

    currentToken = scanner.nextToken();
  }

  private void acceptIt() {
    currentToken = scanner.nextToken();
  }
}
