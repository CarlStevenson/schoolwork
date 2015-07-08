/** @file pc.c
 * A compiler for a subset of Pascal
 */
#include <string.h>
#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include "globals.h"

/**
 * The symbol table.
 */
symtab *idents;

prog *parse_tree;

/**
 * The main program.
 */
int main(int argc, char *argv[]) {
  // Check the arguments.
  if (argc != 2) {
    error("usage: pc file");
  }

  // Check the extension.
  int len = strlen(argv[1]);
  char *ext = argv[1] + len - 2;
  if (strcmp(ext, ".p")) {
    error("%s: Unknown file type", argv[1]);
  }

  // Open the source file.
  if (freopen(argv[1], "r", stdin)  == NULL) {
    error("%s: %s", argv[1], strerror(errno));
  }

  // Open the assembly file.
  char *aname = strdup(argv[1]);
  char *aext = aname + len - 2;
  strcpy(aext, ".s");
  
  if (freopen(aname, "w", stdout) == NULL) {
    error("%s: %s", aname, strerror(errno));
  }

  // Create the symbol table.
  idents = symtab_new(311);

  // Parse the source code.
  yyparse();

  //prog_print(parse_tree);

  declare_variables(parse_tree);

  // Generate intermediate code.
  int n = prog_gen(parse_tree);

  // Emit amd64 code.
  emit_prologue();
  emit(n);
  emit_epilogue();

  // Close the files.
  fclose(stdin);
  fclose(stdout);

  // Assemble and load.
  char *ename = strdup(argv[1]);
  char *eext = ename + len - 2;
  strcpy(eext, "");
  printf("ename: %s\n", ename);

  execlp("gcc", "gcc", "-o", ename, aname, NULL);
}
