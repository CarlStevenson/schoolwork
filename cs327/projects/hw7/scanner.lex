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

"++"      { return T_PP; }
"--"      { return T_MM; }
"+"       { return T_PLUS; }
"-"       { return T_MINUS; }
"*"       { return T_TIMES; }
div       { return T_DIV; }
mod       { return T_MOD; }
":"       { return T_COLON; }
";"       { return T_SEMI; }
"."       { return T_PERIOD; }
","       { return T_COMMA; }
"("       { return T_LPAREN; }
")"       { return T_RPAREN; }
"]"       { return T_RBRACK; }
"["       { return T_LBRACK; }
":="      { return T_ASSIGN; }
"<"       { return T_LT; }
"<="      { return T_LE; }
"="       { return T_EQ; }
"<>"      { return T_NE; }
">="      { return T_GE; }
">"       { return T_GT; }
{SPACE}   { }
if        { return T_IF; }
then      { return T_THEN; }
else      { return T_ELSE; }
while     { return T_WHILE; }
do        { return T_DO; }
program   { return T_PROGRAM; }
var       { return T_VAR; }
integer   { return T_INTEGER; }
boolean   { return T_BOOLEAN; }
begin     { return T_BEGIN; }
end       { return T_END; }
write     { return T_WRITE; }
writeln   { return T_WRITELN; }
read      { return T_READ; }
not       { return T_NOT; }
snlnot    { return T_SNLNOT; }
and       { return T_AND; }
or        { return T_OR; }
true      { return T_TRUE; }
false     { return T_FALSE; }
{CONST}   { yylval.int_const = atol(yytext); return T_CONST; }
{IDENT}   { 
  symbol *sym = symtab_find(idents, yytext);
  if (!sym) sym = symtab_store(idents, yytext, 0);
  yylval.symbol = sym;
  return T_IDENT;
}
.         { error("Invalid character: %c", *yytext); }

%%

