/** @file globals.h
 *  Global constants, variables, types, and function prototypes for the stack compiler.
 */

#ifndef _GLOBALS_H_
#define _GLOBALS_H_

//////////////////
// Symbol Table //
//////////////////

/**
 * A symbol. The value field stores the token value for reserved words.
 */
struct symbol {
  char *name;           /**< The name of the symbol.                  */
  int value;            /**< A value associated with the symbol.      */
  struct symbol *next;  /**< A pointer to the next symbol in a chain. */
};

/**
 * The symbol table.
 */
struct symtab {
  int size;                /**< The size of the table.          */
  struct symbol **chains;  /**< The array of chains of symbols. */
};

/**
 * The symbol table for reserved words.
 */
extern struct symtab *reserved;

/**
 * The symbol table for identifiers.
 */
extern struct symtab *idents;

extern struct symtab *symtab_new(int size);
extern struct symbol *symtab_store(struct symtab *table, char *name, int value);
extern struct symbol *symtab_find(struct symtab *table, char *name);

/////////////
// Scanner //
/////////////

/**
 * The maximum length for an identifier. 
 */
#define MAX_IDENT_LENGTH 256    

/**
 * The lexical value associated with a token.
 * 
 */
union lexical_value {
  long const_value;             /**< The value of an integer constant.          */
  struct symbol *symbol_value;  /**< A pointer to the symbol for an identifier. */
};

extern union lexical_value token_value;

/**
 * Constants for tokens.
 */
enum token_type {
  T_EOF, T_WRITE, T_WRITELN, T_PLUS, T_MINUS, T_TIMES, T_DIV, T_IDENT, T_CONST,
  T_LPAREN, T_RPAREN, T_ASSIGN, T_SEMI, T_IF, T_THEN, T_WHILE, T_DO
};

extern char *token_names[];
extern int scan();

/////////////////////
// Error reporting //
/////////////////////
extern void error(char *format, ... );

////////////
// Parser //
////////////
extern void parse_prog();

/////////////
// Emitter //
/////////////

/**
 * Opcodes.
 */
enum opcode {
  O_ADD, O_SUB, O_MUL, O_DIV, O_WRITE, O_WRITELN, O_PUSH, O_LVALUE, O_RVALUE, O_ASSIGN,
  O_POP, O_COPY, O_GOTO, O_GOTRUE, O_GOFALSE, O_LABEL
};

extern void emit(int opcode, ... );
    
#endif
