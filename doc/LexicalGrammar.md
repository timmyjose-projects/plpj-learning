# Lexical Grammar of Mini-Triangle

```
  Token ::= Letter (Letter | Digit)* | Digit Digit* | + | - | * | / | < | > | = | \ | ; | :(epsilon | =) | ~ | ( | ) | eot
  Separator ::= ! Graphic* eol | space | eol

```
