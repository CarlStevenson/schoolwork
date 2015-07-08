/** @file
 * Intermediate code generation functions.
 */

#include <stdio.h>
#include "globals.h"

/**
 * The next slot in the quad table.
 */
int next = 0;

/**
 * The maximum size for the name of a temportary variable.
 */
#define MAX_TEMP 64

/**
 * The quadruple table
*/
quad quads[MAX_QUADS];

/**
 * Generate a symbol for temporary variables with
 * a unique name.
 */
symbol *temp_new() {
  static int count = 0;
  char name[MAX_TEMP + 1];
  sprintf(name, "T_%d", count++);
  return symtab_store(idents, name, 0);
}

/**
 * Generate a label with
 * a unique name.
 */
symbol *label_new() {
  static int count = 0;
  char name[MAX_TEMP + 1];
  sprintf(name, "L_%d", count++);
  return symtab_store(idents, name, 0);
}

// Forward declaration.
symbol *expr_gen(expr *e);

/**
 * Generate code for an integer constant expression.
 * @param e The expression.
 */
symbol *int_const_expr_gen(int_const_expr *e) {
  quads[next].opcode = O_INT_CONST;
  quads[next].int_const = e->value;
  return quads[next++].result = temp_new();
}

/**
 * Generate code for a variable expression.
 * @param e The expression.
 */
symbol *variable_expr_gen(variable_expr *e) {
  return e->symbol;
}

/**
 * Generate code for a binary expression.
 * @param e The expression.
 */
symbol *binary_expr_gen(binary_expr *e) {
  symbol *left = expr_gen(e->lrand);
  symbol *right = expr_gen(e->rrand);
  switch (e->op) {
  case B_PLUS: quads[next].opcode = O_ADD; break;
  case B_MINUS: quads[next].opcode = O_SUB; break;
  case B_TIMES: quads[next].opcode = O_MUL; break;
  case B_DIV: quads[next].opcode = O_DIV; break;
  }
  quads[next].left = left;
  quads[next].right = right;
  return quads[next++].result = temp_new();
}

/**
 * Generate code for an expression.
 * @param e The expression.
 */
symbol *expr_gen(expr *e) {
  if (next == MAX_QUADS) error("Quad table size exceeded");
  switch(e->tag) {
  case E_INT_CONST: return int_const_expr_gen(e->int_const); break;
  case E_VARIABLE: return variable_expr_gen(e->variable); break;
  case E_BINARY: return binary_expr_gen(e->binary); break;
  }
}

void stmt_gen(stmt *s);

/**
 * Generate code for an if statement
 * @param s The statement.
 */
void if_stmt_gen(if_stmt *s) {
  symbol* out = label_new();
  symbol *result = expr_gen(s->test);
  quads[next].opcode = O_GOFALSE;
  quads[next].left = result;
  quads[next++].right = out;
  stmt_gen(s->body);
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}

/**
 * Generate code for an if else statement
 * @param s The statement.
 */
void if_else_stmt_gen(if_else_stmt *s) {
  symbol *elsee = label_new();
  symbol *out = label_new();
  symbol *result = expr_gen(s->test);
  quads[next].opcode = O_GOFALSE;
  quads[next].left = result;
  quads[next++].right = elsee;
  stmt_gen(s->if_body);
  quads[next].opcode = O_GOTO;
  quads[next++].left = out;
  quads[next].opcode = O_NOOP;
  quads[next++].left = elsee;
  stmt_gen(s->else_body);
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}

/**
 * Generate code for an while statement
 * @param s The statement.
 */
void while_stmt_gen(while_stmt *s) {
  symbol* test = label_new();
  symbol* out = label_new();
  quads[next].opcode = O_NOOP;
  quads[next++].left = test;
  symbol *result = expr_gen(s->test);
  quads[next].opcode = O_GOFALSE;
  quads[next].left = result;
  quads[next++].right = out;
  stmt_gen(s->body);
  quads[next].opcode = O_GOTO;
  quads[next++].left = test;
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}
/**
  * Generate code for a for statement
  * @param s The statement.
  */
  
void for_stmt_gen(for_stmt *s) {

  //labels generated
  symbol* test = label_new();
  symbol* out = label_new();
  //assign the lower bound to the variable  
  symbol *left = expr_gen(s->lbound);
  symbol* upper = expr_gen(s->ubound);
  quads[next].opcode = O_ASSIGN;
  quads[next].left = left;
  quads[next++].result = s->lvalue;
  //set up the label for the code
  // NOOP == "<label>:" for use in assembly code
  quads[next].opcode = O_NOOP;
  quads[next++].left = test;
  //output the test code
  quads[next].opcode = O_LESSTHAN;
  quads[next].left = s->lvalue;
  quads[next].right = upper;
  quads[next++].result = out;
  // output the loop code
  stmt_gen(s->body);
  // increment
  quads[next].opcode = O_INCRE;
  quads[next++].left = s->lvalue;
  // output the loop goto and escape label
  quads[next].opcode = O_GOTO;
  quads[next++].left = test;
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}
/**
 * Generate code for an assignment statement
 * @param s The statement.
 */
void assign_stmt_gen(assign_stmt *s) {
  symbol *left = expr_gen(s->rvalue);
  quads[next].opcode = O_ASSIGN;
  quads[next].left = left;
  quads[next++].result = s->lvalue;
}

/**
 * Generate code for a write statement
 * @param s The statement.
 */
void write_stmt_gen(write_stmt *s) {
  symbol *left = expr_gen(s->arg);
  quads[next].opcode = O_WRITE;
  quads[next++].left = left;
}

/**
 * Generate code for an assignment statement.
 * @param s The statement.
 */
void writeln_stmt_gen(writeln_stmt *s) {
  quads[next++].opcode = O_WRITELN;
}

void stmt_list_gen(stmt_list *l);

/**
 * Generate code for a compound statement.
 * @param s The statement.
 */
void cmpd_stmt_gen(cmpd_stmt *s) {
  stmt_list_gen(s->stmts);
}

/**
 * Generate code for an empty statement.
 * @param s The statement.
 */
void empty_stmt_gen(empty_stmt *s) {
}

/**
 * Generate code for a statement
 * @param s The statement.
 */
void stmt_gen(stmt *s) {
  if (next == MAX_QUADS) error("Quad table size exceeded");
  switch(s->tag) {
  case S_IF: if_stmt_gen(s->iff); break;
  case S_IF_ELSE: if_else_stmt_gen(s->if_else); break;
  case S_WHILE: while_stmt_gen(s->whilee); break;
  case S_ASSIGN: assign_stmt_gen(s->assign); break;
  case S_WRITE: write_stmt_gen(s->write); break;
  case S_WRITELN: writeln_stmt_gen(s->writeln); break;
  case S_CMPD: cmpd_stmt_gen(s->cmpd); break;
  case S_EMPTY: empty_stmt_gen(s->empty); break;
  case S_FOR: for_stmt_gen(s->forr); break;
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
int prog_gen(prog *p) {
  stmt_list_gen(p->body);
  return next;
}


/**
 * Print the quadruple table in human readable format.
 */
void quads_print() {
  int i;
  for (i = 0; i < next; i++) {
    switch (quads[i].opcode) {
    case O_ADD: printf("%s = %s + %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name); break;
    case O_SUB: printf("%s = %s - %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name); break;
    case O_MUL: printf("%s = %s * %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name); break;
    case O_DIV: printf("%s = %s / %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name); break;
    case O_ASSIGN: printf("%s = %s\n", quads[i].result->name, quads[i].left->name); break;
    //case O_FOR: break;
    case O_INT_CONST: printf("%s = %ld\n", quads[i].result->name, quads[i].int_const); break;
    case O_WRITE: printf("write %s\n", quads[i].left->name); break;
    case O_WRITELN: printf("writeln\n"); break;
    case O_GOTRUE: printf("if %s goto %s\n", quads[i].left->name, quads[i].right->name); break;
    case O_GOFALSE: printf("ifnot %s goto %s\n", quads[i].left->name, quads[i].right->name); break;
    case O_GOTO: printf("goto %s\n", quads[i].left->name); break;
    case O_NOOP: printf("%s:\n", quads[i].left->name); break;
    }
  }
}
