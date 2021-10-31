/***
对于文法已经确定的语言来说，使用 frontend 实现编译器是比较舒适的
但是如果文法中途发生变动，就需要每次变动的时候都重新生成一边代码

> antlr4 -no-listener -visitor miniSysY.g4
生成的是 visitor 模式的代码，这里有一个简短的教程
https://wizardforcel.gitbooks.io/antlr4-short-course/content/calculator-visitor.html
***/
grammar miniSysY;

program
  : compUnit
  ;


compUnit
  : funcDef
  ;

funcDef
  : funcType ident LPAREN RPAREN block
  ;

funcType
  : INT
  ;

ident
  : MAIN
  ;

block
  : LBRACE stmt RBRACE
  ;

stmt
  : RETURN number ';';



SEMI : ';' ;

number
  :intConst
  ;

intConst
   : DECIMAL_CONST
   | OCTAL_CONST
   | HEXADECIMAL_CONST
   ;


//注意，以这种方式定义的话，0 是 8 进制的，不过没有影响。
DECIMAL_CONST
   : [1-9]
   | [1-9] [0-9]+
   ;

OCTAL_CONST
   : '0'
   | ('0' [0-7]+)
   ;

HEXADECIMAL_CONST
   : ('0x' | '0X') [a-fA-F0-9]+
   ;

LINE_COMMENT
   : '//' ~ [\r\n]* -> skip // 如果符合 注释的 pattern，直接 skip，下同
   ;

MULTILINE_COMMENT
   : '/*' .*? '*/' -> skip
   ;

WS //空白符跳过
   : [ \r\n\t]+ -> skip
   ;


LPAREN : '(' ;
RPAREN : ')' ;
INT : 'int' ;
MAIN : 'main' ;
LBRACE : '{' ;
RBRACE : '}' ;
RETURN : 'return' ;
