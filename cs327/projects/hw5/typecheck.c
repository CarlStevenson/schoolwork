/** @file typecheck.c
 *  Type checking functions.
 */

#include "globals.h"

/**
 * Traverse the declarations list, storing types in
 * the symbol table.
 * @param p The parse tree.
 */

void declare_variables(prog *p) {
  decl_list *dl = p->decls;
  while (dl) {
    symbol_list *il = dl->first->variables;
    while (il) {
      il->first->type = dl->first->type;
      il = il->next;
    }
    dl = dl->next;
  }
}
