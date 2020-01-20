# Parser Grammar for Mini-Triangle

```
  Program ::= single-Command

  Command ::= single (; single-Command)*

  single-Command ::= Identifier (:= Expression | ( Expression ))
                    | if Expression then single-Command else single-Command
                    | while Expression do single-Command
                    | let Declaration in single-Command
                    | begin Command end
  Expression ::= primary-Expression (Operator primary-Expression)*

  primary-Expression ::= Integer-Literal
                      | Identifier
                      | Operator primary-Expression
                      | ( Expression )

  Declaration ::= single-Declaration (; single-Declaration)*

  single-Declaration ::= const Identifier ~ Expression
                        | var Identifier : Type-Denoter

  Type-Denoter ::= Identifier

```