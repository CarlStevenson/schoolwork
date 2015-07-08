%{
#include <stdlib.h>
#include "globals.h"
#include "parser.h"

YYSTYPE yylval;

%}

%option noyywrap

CONST [0-9]+
IDENT [a-z][a-zA-Z0-9]*
SPACE [ \t\n ]+

%%

"+"       { return T_PLUS; }
"-"       { return T_MINUS; }
"*"       { return T_TIMES; }
div       { return T_DIV; }
";"       { return T_SEMI; }
"("       { return T_LPAREN; }
")"       { return T_RPAREN; }
":="      { return T_ASSIGN; }
{SPACE}   { }
if        { return T_IF; }
then      { return T_THEN; }
else      { return T_ELSE; }
while     { return T_WHILE; }
for       { return T_FOR; }
to        { return T_TO; }
do        { return T_DO; }
begin     { return T_BEGIN; }
end       { return T_END; }
write     { return T_WRITE; }
writeln   { return T_WRITELN; }
{CONST}   { yylval.int_const = atol(yytext); return T_CONST; }
{IDENT}   { 
  symbol *sym = symtab_find(idents, yytext);
  if (!sym) sym = symtab_store(idents, yytext, 0);
  yylval.symbol = sym;
  return T_IDENT;
}
.         { error("Invalid character: %c", *yytext); }

%%

