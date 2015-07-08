/** @file symtab.c
 *  Symbol table functions. A symbol table is a chained hash table.
 */

#include <stdlib.h>
#include <string.h>
#include "globals.h"

/**
 * Create a new symbol table. The implementation is a
 * chained hash table.
 * @param size The size of the table.
 */
symtab *symtab_new(int size) 
{
  symtab *s = malloc(sizeof(symtab));
  s->size = size;
  s->chains = calloc(size, sizeof(symbol *));
  return s;
}

/**
 * Hash a string.
 * @param s The string to hash.
 * @return The hash value of the string.
 */
int hash(char *s) 
{
  int sum = 0;
  while (*s) {
    sum += *s++;
  }
  return sum;
}

/**
 * Store a symbol in a symbol table. The symbol should not already be
 * present.
 * @param table The symbol table.
 * @param name The name of the symbol.
 * @param value A value associated with the symbol.
 * @return A pointer to the new symbol.
 */
symbol *symtab_store(symtab *table, char *name, int value) 
{
  int h = hash(name) % table->size;
  symbol *s = malloc(sizeof(symbol));
  s->name = strdup(name);
  s->value = value;
  s->next = table->chains[h];
  table->chains[h] = s;
  return table->chains[h];
}

/**
 * Find a symbol in a symbol table.
 * @param table The symbol table.
 * @param name The name of the symbol.
 * @return A pointer to the symbol if the name is present in the table,
 * and NULL otherwise.
 */
symbol *symtab_find(symtab *table, char *name) 
{
  int h = hash(name) % table->size;
  symbol *chain = table->chains[h];
  while (chain) {
    if (strcmp(chain->name, name) == 0) return chain;
    else chain = chain->next;
  }
  return NULL;
}
