/** @file sc.c
 * The stack compiler.
 */

#include "globals.h"

/**
 * The symbol table for reserved words.
 */
struct symtab *reserved;

/**
 * The symbol table for identifiers.
 */
struct symtab *idents; 

/**
 * The main program.
 */
int main() {
  reserved = symtab_new(17);
  idents = symtab_new(311);
  symtab_store(reserved, "write", T_WRITE);
  symtab_store(reserved, "writeln", T_WRITELN);
  symtab_store(reserved, "if", T_IF);
  symtab_store(reserved, "then", T_THEN);
  symtab_store(reserved, "while", T_WHILE);
  symtab_store(reserved, "do", T_DO);
  parse_prog();
}
