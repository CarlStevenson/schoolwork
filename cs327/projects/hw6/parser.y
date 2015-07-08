%{
  #include <stdio.h>
  #include "globals.h"
%}

%type <stmt_list>        stmt_list
%type <stmt>             stmt
%type <if_stmt>          if_stmt
%type <if_else_stmt>     if_else_stmt
%type <while_stmt>       while_stmt
%type <assign_stmt>      assign_stmt
%type <unary_stmt>       unary_stmt
%type <write_stmt>       write_stmt
%type <writeln_stmt>     writeln_stmt
%type <empty_stmt>       empty_stmt
%type <cmpd_stmt>        cmpd_stmt
%type <expr>             expr
%type <int_const_expr>   int_const_expr
%type <bool_const_expr>  bool_const_expr
%type <unary_expr>       unary_expr
%type <binary_expr>      binary_expr
%type <binary_expr>      rel_expr
%type <variable_expr>    variable_expr
%type <decl_list>        decls
%type <decl_list>        decl_list
%type <decl>             decl
%type <symbol_list>      ident_list
%token <int_const> T_CONST
%token <symbol> T_IDENT
%right T_SNLNOT
%token T_LPAREN T_RPAREN T_ASSIGN T_SEMI T_PERIOD T_COLON T_COMMA
%token T_VAR T_INTEGER T_NOT T_BOOLEAN
%token T_IF T_THEN T_ELSE T_WHILE T_DO
%token T_PROGRAM T_BEGIN T_END
%token T_WRITE T_WRITELN 
%token T_PP T_MM
%token T_FALSE T_TRUE
%left T_OR
%left T_AND
%nonassoc T_EQ T_NE T_LT T_LE T_GE T_GT
%left T_PLUS T_MINUS
%left T_TIMES T_DIV T_MOD
%right T_UPLUS T_UMINUS
%start prog

%%

prog :
  T_PROGRAM T_IDENT T_SEMI decls cmpd_stmt T_PERIOD { parse_tree = prog_new($2, $4, stmt_new(S_CMPD, $5)); }
;

decls :
  T_VAR decl_list { $$ = $2; }
|                 { $$ = NULL; }
;

ident_list :
  T_IDENT { $$ = symbol_list_new($1, NULL); }
| T_IDENT T_COMMA ident_list { $$ = symbol_list_new($1, $3); }
;

decl :
  ident_list T_COLON T_INTEGER T_SEMI { $$ = decl_new($1, TY_INTEGER); }
| ident_list T_COLON T_BOOLEAN T_SEMI { $$ = decl_new($1, TY_BOOLEAN); }
;

decl_list :
  decl           { $$ = decl_list_new($1, NULL); }
| decl decl_list { $$ = decl_list_new($1, $2); }
;

stmt_list :
  stmt { $$ = stmt_list_new($1, NULL); }
| stmt T_SEMI stmt_list { $$ = stmt_list_new($1, $3); }
;

stmt :
  if_stmt      { $$ = stmt_new(S_IF, $1); }
| if_else_stmt { $$ = stmt_new(S_IF_ELSE, $1); }
| while_stmt   { $$ = stmt_new(S_WHILE, $1); }
| assign_stmt  { $$ = stmt_new(S_ASSIGN, $1); }
| unary_stmt   { $$ = stmt_new(S_UNARY, $1); }
| write_stmt   { $$ = stmt_new(S_WRITE, $1); }
| writeln_stmt { $$ = stmt_new(S_WRITELN, $1); }
| cmpd_stmt    { $$ = stmt_new(S_CMPD, $1); }
| empty_stmt   { $$ = stmt_new(S_EMPTY, $1); }
;

if_stmt :
  T_IF expr T_THEN stmt { $$ = if_stmt_new($2, $4); }
;

if_else_stmt :
  T_IF expr T_THEN stmt T_ELSE stmt { $$ = if_else_stmt_new($2, $4, $6); }
;

while_stmt :
  T_WHILE expr T_DO stmt { $$ = while_stmt_new($2, $4); }
;

assign_stmt :
  T_IDENT T_ASSIGN expr      { $$ = assign_stmt_new($1, $3); }
;

unary_stmt :
  expr { $$ = unary_stmt_new($1); }
;

write_stmt :
  T_WRITE T_LPAREN expr T_RPAREN { $$ = write_stmt_new($3); }
;

writeln_stmt :
  T_WRITELN T_LPAREN T_RPAREN { $$ = writeln_stmt_new(); }
;

empty_stmt :
  { $$ = empty_stmt_new(); }
;

cmpd_stmt :
  T_BEGIN stmt_list T_END { $$ = cmpd_stmt_new($2); }
;

expr :
  int_const_expr  { $$ = expr_new(E_INT_CONST, $1); }
| variable_expr    { $$ = expr_new(E_VARIABLE, $1); }
| bool_const_expr { $$ = expr_new(E_BOOL_CONST, $1); }
| unary_expr      { $$ = expr_new(E_UNARY, $1); }
| binary_expr     { $$ = expr_new(E_BINARY, $1); }
| rel_expr        { $$ = expr_new(E_BINARY, $1); }
| T_LPAREN expr T_RPAREN { $$ = $2; }
;

rel_expr :
  expr T_LT expr    { $$ = binary_expr_new(B_LT, $1, $3); }
| expr T_LE expr    { $$ = binary_expr_new(B_LE, $1, $3); }
| expr T_EQ expr    { $$ = binary_expr_new(B_EQ, $1, $3); }
| expr T_NE expr    { $$ = binary_expr_new(B_NE, $1, $3); }
| expr T_GE expr    { $$ = binary_expr_new(B_GE, $1, $3); }
| expr T_GT expr    { $$ = binary_expr_new(B_GT, $1, $3); }
;

int_const_expr :
  T_CONST { $$ = int_const_expr_new($1); }
;

bool_const_expr :
  T_TRUE  { $$ = bool_const_expr_new(1); }
| T_FALSE { $$ = bool_const_expr_new(0); }
;

variable_expr :
  T_IDENT { $$ = variable_expr_new($1); }
;

unary_expr :
  T_PLUS expr %prec T_UPLUS   { $$ = unary_expr_new(U_PLUS, $2); }
| T_MINUS expr %prec T_UMINUS { $$ = unary_expr_new(U_MINUS, $2); }
| T_NOT expr %prec T_UPLUS   { $$ = unary_expr_new(U_NOT, $2); }
| expr T_SNLNOT   { $$ = unary_expr_new(U_NOT, $1); }
| T_PP T_IDENT %prec T_UPLUS  { $$ = unary_expr_new(U_INCRE, expr_new(E_VARIABLE, variable_expr_new($2))); }
| T_MM T_IDENT %prec T_UMINUS { $$ = unary_expr_new(U_DECRE, expr_new(E_VARIABLE, variable_expr_new($2))); }
| T_IDENT T_PP %prec T_ASSIGN{ $$ = unary_expr_new(U_INCRE, expr_new(E_VARIABLE, variable_expr_new($1))); }
| T_IDENT T_MM %prec T_ASSIGN { $$ = unary_expr_new(U_DECRE, expr_new(E_VARIABLE, variable_expr_new($1))); }
;

binary_expr :
  expr T_PLUS  expr { $$ = binary_expr_new(B_PLUS, $1, $3); }
| expr T_MINUS expr { $$ = binary_expr_new(B_MINUS, $1, $3); }
| expr T_TIMES expr { $$ = binary_expr_new(B_TIMES, $1, $3); }
| expr T_DIV expr   { $$ = binary_expr_new(B_DIV, $1, $3); }
| expr T_MOD expr   { $$ = binary_expr_new(B_MOD, $1, $3); }
| expr T_AND expr   { $$ = binary_expr_new(B_AND, $1, $3); }
| expr T_OR expr    { $$ = binary_expr_new(B_OR, $1, $3); }
;

%%
