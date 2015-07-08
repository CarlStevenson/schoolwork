/** @file
 * Code generation functions.
 */

#include "globals.h"

void expr_gen(expr *e);

/**
 * Generate code for an integer constant expression.
 * @param e The expression.
 */
void int_const_expr_gen(int_const_expr *e) {
  emit(O_PUSH, e->value);
}

/**
 * Generate code for a variable expression.
 * @param e The expression.
 */
void variable_expr_gen(variable_expr *e) {
  emit(O_RVALUE, e->symbol);
}

/**
 * Generate code for a binary expression.
 * @param e The expression.
 */
void binary_expr_gen(binary_expr *e) {
  expr_gen(e->lrand);
  expr_gen(e->rrand);
  switch (e->op) {
  case B_PLUS: emit(O_ADD); break;
  case B_MINUS: emit(O_SUB); break;
  case B_TIMES: emit(O_MUL); break;
  case B_DIV: emit(O_DIV); break;
  }
}

/**
 * Generate code for an expression.
 * @param e The expression.
 */
void expr_gen(expr *e) {
  switch(e->tag) {
  case E_INT_CONST: int_const_expr_gen(e->int_const); break;
  case E_VARIABLE: variable_expr_gen(e->variable); break;
  case E_BINARY: binary_expr_gen(e->binary); break;
  }
}

/**
 * Generate code for an assignment statement
 * @param s The statement.
 */
void assign_stmt_gen(assign_stmt *s) {
  emit(O_LVALUE, s->lvalue);
  expr_gen(s->rvalue);
  emit(O_ASSIGN);
}

/**
 * Generate code for an assignment statement
 * @param s The statement.
 */
void write_stmt_gen(write_stmt *s) {
  expr_gen(s->arg);
  emit(O_WRITE);
}

/**
 * Generate code for an assignment statement
 * @param s The statement.
 */
void writeln_stmt_gen(writeln_stmt *s) {
  emit(O_WRITELN);
}
  
/**
 * Generate code for a statement
 * @param s The statement.
 */
void stmt_gen(stmt *s) {
  switch(s->tag) {
  case S_ASSIGN: assign_stmt_gen(s->assign); break;
  case S_WRITE: write_stmt_gen(s->write); break;
  case S_WRITELN: writeln_stmt_gen(s->writeln); break;
  }
}

/**
 * Generate code for a statement list
 * @param l The statement list.
 */
void stmt_list_gen(stmt_list *l) {
  if (l) {
    stmt_gen(l->first);
    stmt_list_gen(l->next);
  }
}

/**
 * Generate code for a program.
 * @param p The program.
 */
void prog_gen(prog *p) {
  stmt_list_gen(p->body);
}
