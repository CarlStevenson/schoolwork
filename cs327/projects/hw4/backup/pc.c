/** @file pc.c
 * A compiler for a subset of Pascal
 */
#include "globals.h"

/**
 * The symbol table.
 */
symtab *idents;

prog *parse_tree;

/**
 * The main program.
 */
int main() {
  // Create the symbol table.
  idents = symtab_new(311);

  // Parse the source code.
  yyparse();

  // Generate intermediate code.
  int n = prog_gen(parse_tree);

  // Emit amd64 code.
  emit_prologue();
  emit(n);
  emit_epilogue();
}
