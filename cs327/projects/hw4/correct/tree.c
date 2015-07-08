/** @file
 * Constructors for the parse tree.
 */

#include <stdlib.h>
#include <stdarg.h>
#include "globals.h"

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
  case E_INT_CONST: e->int_const = va_arg(args, int_const_expr *); break;
  case E_VARIABLE: e->variable = va_arg(args, variable_expr *); break;
  case E_BINARY: e->binary = va_arg(args, binary_expr *); break;
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
 * Create a for statement.
 * @param lvalue The lvalue.
 * @param lbound The lower bound.
 * @param ubound The upper bound.
 * @param body The body of the statement
 * @return The new statement.
 */
 
for_stmt *for_stmt_new(symbol *lvalue, expr *lbound, expr *ubound, stmt *body) {
  for_stmt *s = malloc(sizeof(for_stmt));
  s->lvalue = lvalue;
  s->lbound = lbound;
  s->ubound = ubound;
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

/**
 * Create a write statement.
 * @param arg The argument.
 * @return The new statement.
 */
write_stmt *write_stmt_new(expr *arg) {
  write_stmt *s = malloc(sizeof(write_stmt));
  s->arg = arg;
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
  case S_FOR: s->forr = va_arg(args, for_stmt *); break;
  case S_ASSIGN: s->assign = va_arg(args, assign_stmt *); break;
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
 * Create a program.
 * @param body The body of the program.
 * @return The new statement.
 */
prog *prog_new(stmt_list *body) {
  prog *p = malloc(sizeof(prog));
  p->body = body;
  return p;
}
