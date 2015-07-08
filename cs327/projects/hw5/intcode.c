/** @file
 * Intermediate code generation functions.
 */

#include <stdio.h>
#include <string.h>
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
  case B_MOD: quads[next].opcode = O_MOD; break;
  }
  quads[next].left = left;
  quads[next].right = right;
  return quads[next++].result = temp_new();
}

/**
 * Generate code for a unary expression.
 * @param e The expression.
 */

 symbol *unary_expr_gen(unary_expr *e) {
  symbol *left = expr_gen(e->orand);
  switch (e->op) {
    case U_PLUS: quads[next].opcode = O_UPLUS; break;
    case U_MINUS: quads[next].opcode = O_UMINUS; break;
    case U_NOT: quads[next].opcode = O_NOT; break;
    case U_INCRE: quads[next].opcode = O_INCRE; break;
    case U_DECRE: quads[next].opcode = O_DECRE; break;
  }
  quads[next].left = left;
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
  case E_UNARY: return unary_expr_gen(e->unary); break;
  }
}

void bool_expr_gen(expr *e, symbol *true, symbol *false);

/**
 * Generate code for a Boolean unary expression.
 * @param e The expression.
* @param true The label to jump to if the expression evaluates to true.
 * @param false The label to jump to if the expression evaluates to false.
 */

void bool_unary_expr_gen(unary_expr *e, symbol*true, symbol *false){
  switch(e->op){
    case U_NOT:
          bool_expr_gen(e->orand,false,true);

  } 
  //quads[next].result = temp_new();
  //quads[next++].left = left;

}

/**
 * Generate code for a relational expression.
 * @param e The expression.
 * @param true The label to jump to if the expression evaluates to true.
 * @param false The label to jump to if the expression evaluates to false.
 */
void rel_expr_gen(binary_expr *e, symbol *true, symbol *false) {
  symbol *left = expr_gen(e->lrand);
  symbol *right = expr_gen(e->rrand);
  switch (e->op) {
  case B_LE: quads[next].opcode = O_GOLE; break;
  case B_LT: quads[next].opcode = O_GOLT; break;
  case B_EQ: quads[next].opcode = O_GOEQ; break;
  case B_NE: quads[next].opcode = O_GONE; break;
  case B_GT: quads[next].opcode = O_GOGT; break;
  case B_GE: quads[next].opcode = O_GOGE; break;
  }
  quads[next].left = left;
  quads[next].right = right;
  quads[next++].result = true;
  quads[next].opcode = O_GOTO;
  quads[next++].result = false;
}
    
/**
 * Generate code for a Boolean constant expression.
 * @param e The expression.
 * @param true The label to jump to if the expression evaluates to true.
 * @param false The label to jump to if the expression evaluates to false.
 */
void bool_const_expr_gen(int_const_expr *e, symbol *true, symbol *false) {
  quads[next].opcode = O_GOTO;
  if (e->value) {
    quads[next++].result = true;
  }
  else {
    quads[next++].result = false;
  }
}

/**
 * Generate code for a Boolean variable expression.
 * @param e The expression.
 * @param true The label to jump to if the expression evaluates to true.
 * @param false The label to jump to if the expression evaluates to false.
 */
void bool_variable_expr_gen(variable_expr *e, symbol *true, symbol *false) {
  quads[next].opcode = O_GOTRUE;
  quads[next].left = e->symbol;
  quads[next++].result = true;
  quads[next].opcode = O_GOTO;
  quads[next++].result = false;
}

/**
 * Generate code for a Boolean binary expression.
 * @param e The expression.
 * @param true The label to jump to if the expression evaluates to true.
 * @param false The label to jump to if the expression evaluates to false.
 */
void bool_binary_expr_gen(binary_expr *e, symbol *true, symbol *false) {
  symbol *left_false;
  symbol *left_true;
  switch(e->op) {
  case B_OR: 
    left_false = label_new();
    bool_expr_gen(e->lrand, true, left_false);
    quads[next].opcode = O_NOOP;
    quads[next++].left = left_false;
    bool_expr_gen(e->rrand, true, false);
    break;
  case B_AND:
    left_true = label_new();
    bool_expr_gen(e->lrand, left_true, false);
    quads[next].opcode = O_NOOP;
    quads[next++].left = left_true;
    bool_expr_gen(e->rrand, true, false);
    break;
  case B_LE:
  case B_LT:
  case B_EQ:
  case B_NE:
  case B_GT:
  case B_GE: rel_expr_gen(e, true, false); break;
  default:
    binary_expr_gen(e);
    break;
  }
}



/**
 * Generate code for a Boolean expression.
 * 
 */
void bool_expr_gen(expr *e, symbol *true, symbol *false) {
  if (next == MAX_QUADS) error("Quad table size exceeded");
  switch(e->tag) {
  case E_BOOL_CONST: bool_const_expr_gen(e->int_const, true, false); break;
  case E_VARIABLE: bool_variable_expr_gen(e->variable, true, false); break;
  case E_BINARY: bool_binary_expr_gen(e->binary, true, false); break;
  case E_UNARY: bool_unary_expr_gen(e->unary, true, false); break;
  }
}

void stmt_gen(stmt *s);

/**
 * Generate code for an if statement
 * @param s The statement.
 */
void if_stmt_gen(if_stmt *s) {
  symbol* out = label_new();
  symbol *true = label_new();
  bool_expr_gen(s->test, true, out);
  quads[next].opcode = O_NOOP;
  quads[next++].left = true;
  stmt_gen(s->body);
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}

/**
 * Generate code for an if else statement
 * @param s The statement.
 */
void if_else_stmt_gen(if_else_stmt *s) {
  symbol *out = label_new();
  symbol *true = label_new();
  symbol *false = label_new();
  bool_expr_gen(s->test, true, false);
  quads[next].opcode = O_NOOP;
  quads[next++].left = true;
  stmt_gen(s->if_body);
  quads[next].opcode = O_GOTO;
  quads[next++].result = out;
  quads[next].opcode = O_NOOP;
  quads[next++].left = false;
  stmt_gen(s->else_body);
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}

/**
 * Generate code for an while statement
 * @param s The statement.
 */
void while_stmt_gen(while_stmt *s) {
  symbol *out = label_new();
  symbol *begin = label_new();
  symbol *true = label_new();
  symbol *false = out;
  quads[next].opcode = O_NOOP;
  quads[next++].left = begin;
  bool_expr_gen(s->test, true, false);
  quads[next].opcode = O_NOOP;
  quads[next++].left = true;
  stmt_gen(s->body);
  quads[next].opcode = O_GOTO;
  quads[next++].result = begin;
  quads[next].opcode = O_NOOP;
  quads[next++].left = out;
}

/**
 * Generate code for an assignment statement
 * @param s The statement.
 */
void assign_stmt_gen(assign_stmt *s) {
  /*symbol *left = expr_gen(s->rvalue);
  quads[next].opcode = O_ASSIGN;
  quads[next].left = left;
  quads[next++].result = s->lvalue;*/
  symbol *left, *true, *false, *out;
  switch (s->lvalue->type) {
  case TY_INTEGER:
    left = expr_gen(s->rvalue);
    quads[next].opcode = O_ASSIGN;
    quads[next].left = left;
    quads[next++].result = s->lvalue;
    break;
  case TY_BOOLEAN:
    true = label_new();
    false = label_new();
    out = label_new();
    bool_expr_gen(s->rvalue, true, false);
    quads[next].opcode = O_NOOP;
    quads[next++].left = true;
    quads[next].opcode = O_INT_CONST;
    quads[next].int_const = 1;
    quads[next++].result = s->lvalue;
    quads[next].opcode = O_GOTO;
    quads[next++].result = out;
    quads[next].opcode = O_NOOP;
    quads[next++].left = false;
    quads[next].opcode = O_INT_CONST;
    quads[next].int_const = 0;
    quads[next++].result = s->lvalue;
    quads[next].opcode = O_NOOP;
    quads[next++].left = out;
    break;
  }
}

void unary_stmt_gen(unary_stmt *s) {
  symbol *left = expr_gen(s->value);
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
  case S_UNARY: unary_stmt_gen(s->sunary); break;
  case S_WRITE: write_stmt_gen(s->write); break;
  case S_WRITELN: writeln_stmt_gen(s->writeln); break;
  case S_CMPD: cmpd_stmt_gen(s->cmpd); break;
  case S_EMPTY: empty_stmt_gen(s->empty); break;
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
 * Delete a quad. The remainder of the quad table slides down.
 * @param i The index of the quad to delete.
 */
void delete_quad(int i) {
  memmove(quads+i, quads+i+1, (next - i) * sizeof(quad));
  next--;
}

/**
 * Remove gotos when the target label is the next quad.
 */
void eliminate_goto_next() {
  int i;
  for (i = 0; i < next; i++) {
    if (i+1 < next && quads[i].opcode == O_GOTO && quads[i+1].opcode == O_NOOP && quads[i].result == quads[i+1].left) {
      delete_quad(i);
    }
  }
}

/**
 * Mark any labels that are targets of jumps.
 */
void mark_target_labels() {
  int i;
  for (i = 0; i < next; i++) {
    switch (quads[i].opcode) {
    case O_GOTO:
    case O_GOFALSE:
    case O_GOTRUE:
    case O_GOLT:
    case O_GOLE:
    case O_GOEQ:
    case O_GONE:
    case O_GOGT:
    case O_GOGE:
      if (quads[i].result) {
	      quads[i].result->value = 1;
      }
      break;
    }
  }
}

/**
 * Remove any labels that are not the targets of jumps. This function assumes that
 * any jump targets have already been marked by a call to mark_target_labels.
 */
void eliminate_unused_labels() {
  int i;
  for (i = 0; i < next; i++) {
    if (quads[i].opcode == O_NOOP && !quads[i].left->value) {
      delete_quad(i);
    }
  }
}

/**
 * Generate code for a program.
 * @param p The program.
 */
int prog_gen(prog *p) {
  stmt_gen(p->body);
  eliminate_goto_next();
  mark_target_labels();
  eliminate_unused_labels();
  return next;
}
