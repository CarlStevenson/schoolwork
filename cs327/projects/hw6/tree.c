/** @file
 * Constructors for the parse tree.
 */

#include <stdlib.h>
#include <stdarg.h>
#include <stdio.h>
#include "globals.h"

/**
 * Create a Boolean constant expression.
 * @param value The value of the expression.
 * @return The new expression.
 */
bool_const_expr *bool_const_expr_new(long value) {
  bool_const_expr *e = malloc(sizeof(bool_const_expr));
  e->value = value;
  return e;
}

/**
 * Create an integer constant expression.
 * @param value The value of the expression.
 * @return The new expression.
 */
int_const_expr *int_const_expr_new(long value) {
  int_const_expr *e = malloc(sizeof(int_const_expr));
  e->value = value;
  return e;
}

/**
 * Create a variable expression.
 * @param symbol The symbol representing the variable.
 * @return The new expression.
 */
variable_expr *variable_expr_new(symbol *symbol) {
  variable_expr *e = malloc(sizeof(variable_expr));
  e->symbol = symbol;
  return e;
}

/**
 * Create an expression with a unary operator.
 * @param op The operator.
 * @param rand The operand.
 * @return The new expression.
 */
unary_expr *unary_expr_new(int op, expr *rand) {
  unary_expr *e = malloc(sizeof(unary_expr));
  e->op = op;
  e->rand = rand;
  typecheck(rand);
  return e;
}

/**
 * Create an expression with a binary operator.
 * @param op The operator.
 * @param lrand The left operand.
 * @param rrand The right operand.
 * @return The new expression.
 */
binary_expr *binary_expr_new(int op, expr *lrand, expr *rrand) {
  binary_expr *e = malloc(sizeof(binary_expr));
  e->op = op;
  e->lrand = lrand;
  e->rrand = rrand;

  return e;
}

/**
 * Create an expression.
 * @param tag The tagfield.
 * @param ... An expression of a specific type.
 * @return The new expression.
 */
expr *expr_new(expr_tag tag, ... ) {
  va_list args;
  expr *e = malloc(sizeof(expr));
  e->tag = tag;
  va_start(args, tag);
  switch (tag) {
  case E_BOOL_CONST: e->bool_const = va_arg(args, bool_const_expr *); break;
  case E_INT_CONST: e->int_const = va_arg(args, int_const_expr *); break;
  case E_VARIABLE: e->variable = va_arg(args, variable_expr *); break;
  case E_BINARY: e->binary = va_arg(args, binary_expr *); break;
  case E_UNARY: e->unary = va_arg(args, unary_expr *); break;
  }
  return e;
}

/**
 * Create an if statement.
 * @param test The test expression.
 * @param body The body of the statement.
 * @return The new statement.
 */
if_stmt *if_stmt_new(expr *test, stmt *body) {
  if_stmt *s = malloc(sizeof(if_stmt));
  s->test = test;
  s->body = body;
  return s;
}


/**
 * Create an if else statement.
 * @param test The test expression.
 * @param if_body The if part of the statement.
 * @param else_body The else part of the statement.
 * @return The new statement.
 */
if_else_stmt *if_else_stmt_new(expr *test, stmt *if_body, stmt *else_body) {
  if_else_stmt *s = malloc(sizeof(if_else_stmt));
  s->test = test;
  s->if_body = if_body;
  s->else_body = else_body;
  return s;
}

/**
 * Create a while statement.
 * @param test The test expression.
 * @param body The body of the statement.
 * @return The new statement.
 */
while_stmt *while_stmt_new(expr *test, stmt *body) {
  while_stmt *s = malloc(sizeof(while_stmt));
  s->test = test;
  s->body = body;
  return s;
}

/**
 * Create an assignment statement.
 * @param lvalue The lvalue.
 * @param rvalue The rvalue.
 * @return The new statement.
 */
assign_stmt *assign_stmt_new(symbol *lvalue, expr *rvalue) {
  assign_stmt *s = malloc(sizeof(assign_stmt));
  s->lvalue = lvalue;
  s->rvalue = rvalue;
  return s;
}

unary_stmt *unary_stmt_new(expr *value) {
  unary_stmt *s = malloc(sizeof(unary_stmt));
  s->value = value; 
  return s;
}

/**
 * Create a write statement.
 * @param arg The argument.
 * @return The new statement.
 */
write_stmt *write_stmt_new(expr *arg) {
  write_stmt *s = malloc(sizeof(write_stmt));
  s->arg = arg;
  if(isBoolean(arg)){
    error("Type mismatch error");
  }
  return s;
}

/**
 * Create a writeln statement.
 * @return The new statement.
 */
writeln_stmt *writeln_stmt_new() {
  writeln_stmt *s = malloc(sizeof(writeln_stmt));
  return s;
}

/**
 * Create a statement.
 * @param tag The tagfield.
 * @param ... A statement of a specific type.
 * @return The new statement.
 */
stmt *stmt_new(stmt_tag tag, ... ) {
  va_list args;
  stmt *s = malloc(sizeof(stmt));
  s->tag = tag;
  va_start(args, tag);
  switch (tag) {
  case S_IF: s->iff = va_arg(args, if_stmt *); break;
  case S_IF_ELSE: s->if_else = va_arg(args, if_else_stmt *); break;
  case S_WHILE: s->whilee = va_arg(args, while_stmt *); break;
  case S_ASSIGN: s->assign = va_arg(args, assign_stmt *); break;
  case S_UNARY: s->sunary = va_arg(args, unary_stmt *); break;
  case S_WRITE: s->write = va_arg(args, write_stmt *); break;
  case S_WRITELN: s->writeln = va_arg(args, writeln_stmt *); break;
  case S_CMPD: s->cmpd = va_arg(args, cmpd_stmt *); break;
  case S_EMPTY: s->empty = va_arg(args, empty_stmt *); break;
  }
  return s;
}

/**
 * Create a statement list.
 * @param first The first statement in the list.
 * @param next The rest of the statement list.
 * @return The new statement.
 */
stmt_list *stmt_list_new(stmt *first, stmt_list *next) {
  stmt_list *l = malloc(sizeof(stmt_list));
  l->first = first;
  l->next = next;
  return l;
}

/**
 * Create a compound statement.
 * @param stmts The list of statements.
 * @return The new statement.
 */
cmpd_stmt *cmpd_stmt_new(stmt_list *stmts) {
  cmpd_stmt *s = malloc(sizeof(cmpd_stmt));
  s->stmts = stmts;
  return s;
}

/**
 * Create an empty statement.
 * @return The new statement.
 */
empty_stmt *empty_stmt_new() {
  empty_stmt *s = malloc(sizeof(empty_stmt));
  return s;
}

/**
 * Create a symbol list.
 * @param first The first symbol in the list.
 * @param next The remainder of the list.
 * @return The new symbol list.
 */
symbol_list *symbol_list_new(symbol *first, symbol_list *next) {
  symbol_list *l = malloc(sizeof(symbol_list));
  l->first = first;
  l->next = next;
  return l;
}

/**
 * Create a declaration.
 * @param variables The list of variables.
 * @param type The type of the variables.
 */
decl *decl_new(symbol_list *variables, type type) {
  decl *d = malloc(sizeof(decl));
  d->variables = variables;
  d->type = type;
}
  
/**
 * Create a declaration list.
 * @param first The first declaration in the list.
 * @param next The remainder of the list.
 * @return The new declaration list.
 */

decl_list *decl_list_new(decl *first, decl_list *next) {
  decl_list *l = malloc(sizeof(decl_list));
  l->first = first;
  l->next = next;
  return l;
}

/**
 * Create a program.
 * @param name The name of the program.
 * @param decls The list of declarations.
 * @param body The body of the program.
 * @return The new statement.
 */
prog *prog_new(symbol *name, decl_list *decls, stmt *body) {
  prog *p = malloc(sizeof(prog));
  p->name = name;
  p->decls = decls;
  p->body = body;
  return p;
}

#define INDENT 2   /**< The number of spaces for each level of indentation when printing the parse tree. */

/**
 * Print an expression in human readable format to stderr.
 * @param e The expression to print.
 * @param indent The amount of indentation in spaces.
 */
void expr_print(expr *e, int indent) {
  switch (e->tag) {
  case E_BOOL_CONST:
    fprintf(stderr, "%*sbool const %s\n", indent, "", e->bool_const->value ? "true" : "false");
    break;
  case E_INT_CONST:
    fprintf(stderr, "%*sint const %ld\n", indent, "", e->int_const->value);
    break;
  case E_VARIABLE:
    fprintf(stderr, "%*svariable %s\n", indent, "", e->variable->symbol->name);
    break;
  case E_UNARY:
    fprintf(stderr, "%*sunary ", indent, "");
    switch (e->unary->op) {
    case U_PLUS: fprintf(stderr, "plus\n"); break;
    case U_MINUS: fprintf(stderr, "minus\n"); break;
    case U_NOT: fprintf(stderr, "not\n"); break;
    case U_INCRE: fprintf(stderr, "increment\n"); break;
    case U_DECRE: fprintf(stderr, "decrement\n"); break;
    }
    expr_print(e->unary->rand, indent + INDENT);
    break;
  case E_BINARY:
    fprintf(stderr, "%*sbinary ", indent, "");
    switch (e->binary->op) {
    case B_PLUS: fprintf(stderr, "plus\n"); break;
    case B_MINUS: fprintf(stderr, "minus\n"); break;
    case B_TIMES: fprintf(stderr, "times\n"); break;
    case B_DIV: fprintf(stderr, "div\n"); break;
    case B_MOD: fprintf(stderr, "mod\n"); break;
    case B_AND: fprintf(stderr, "and\n"); break;
    case B_OR: fprintf(stderr, "or\n"); break;
    case B_LT: fprintf(stderr, "lt\n"); break;
    case B_LE: fprintf(stderr, "le\n"); break;
    case B_EQ: fprintf(stderr, "eq\n"); break;
    case B_NE: fprintf(stderr, "ne\n"); break;
    case B_GE: fprintf(stderr, "ge\n"); break;
    case B_GT: fprintf(stderr, "gt\n"); break;
    }
    expr_print(e->binary->lrand, indent + INDENT);
    expr_print(e->binary->rrand, indent + INDENT);
    break;
  }
}

void stmt_list_print(stmt_list *l, int indent);

/**
 * Print a statement in human readable format to stderr.
 * @param s The statement to be printed.
 * @param indent The amount of indentation in spaces.
 */
void stmt_print(stmt *s, int indent) {
  switch (s->tag) {
  case S_IF:
    fprintf(stderr, "%*sif\n", indent, "");
    expr_print(s->iff->test, indent);
    stmt_print(s->iff->body, indent + INDENT);
    break;
  case S_IF_ELSE:
    fprintf(stderr, "%*sif else\n", indent, "");
    expr_print(s->if_else->test, indent + INDENT);
    stmt_print(s->if_else->if_body, indent + INDENT);
    stmt_print(s->if_else->else_body, indent + INDENT);
    break;
  case S_WHILE:
    fprintf(stderr, "%*swhile\n", indent, "");
    expr_print(s->whilee->test, indent + INDENT);
    stmt_print(s->whilee->body, indent + INDENT);
    break;  
  case S_CMPD:
    fprintf(stderr, "%*scompound\n", indent, "");
    stmt_list_print(s->cmpd->stmts, indent + INDENT);
    break;  
  case S_ASSIGN:
    fprintf(stderr, "%*sassign to %s\n", indent, "", s->assign->lvalue->name);
    expr_print(s->assign->rvalue, indent + INDENT);
    break;
  case S_UNARY:
    fprintf(stderr, "%*sunary op\n", indent, "");
    expr_print(s->sunary->value, indent + INDENT);
    break;
  case S_WRITE:
    fprintf(stderr, "%*swrite\n", indent, "");
    expr_print(s->write->arg, indent + INDENT);
    break;  
  case S_WRITELN:
    fprintf(stderr, "%*swriteln\n", indent, "");
    break;  
  case S_EMPTY:
    fprintf(stderr, "%*sempty\n", indent, "");
    break;  
  }
}

/**
 * Print a statement list in human readable format to stderr.
 * @param l The statement list to be printed.
 * @param indent The amount of indentation in spaces.
 */
void stmt_list_print(stmt_list *l, int indent) {
  while (l) {
    stmt_print(l->first, indent);
    l = l->next;
  }
}

/**
 * Print a declaration.
 * @param d The declaration to be printed.
 * @param indent The amount of indentation in spaces.
 */
void decl_print(decl *d, int indent) {
  fprintf(stderr, "%*sdeclaration", indent, "");
  symbol_list *l = d->variables;
  while (l) {
    fprintf(stderr, " %s", l->first->name);
    l = l->next;
  }
  switch (d->type) {
  case TY_INTEGER: fprintf(stderr, " integer\n"); break;
  case TY_BOOLEAN: fprintf(stderr, " boolean\n"); break;
  }
}

/**
 * Print a declaration list in human readable format to stderr.
 * @param l The parameter list to be printed.
 * @param indent The amount of indentation in spaces.
 */
void decl_list_print(decl_list *l, int indent) {
  while (l) {
    decl_print(l->first, indent);
    l = l->next;
  }
}

/**
 * Print the parse tree in human readable format to stderr.
 * @param p The parse tree.
 */
void prog_print(prog *p) {
  fprintf(stderr, "prog\n");
  decl_list_print(p->decls, INDENT);
  stmt_print(p->body, INDENT);
}
