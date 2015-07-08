/** @file globals.h
 *  Global constants, variables, types, and function prototypes for the stack compiler.
 */

#ifndef _GLOBALS_H_
#define _GLOBALS_H_

//////////////////
// Symbol Table //
//////////////////

/**
 * Constants for types.
 */
typedef enum { 
  TY_NONE, TY_INTEGER, TY_BOOLEAN
} type;

/**
 * A symbol. The value field stores the token value for reserved words.
 */
typedef struct symbol {
  char *name;           /**< The name of the symbol.                  */
  int value;            /**< A value associated with the symbol.      */
  struct symbol *next;  /**< A pointer to the next symbol in a chain. */
  type type;            /**< The type of the symbol.                  */
} symbol;

/**
 * The symbol table.
 */
typedef struct {
  int size;         /**< The size of the table.          */
  symbol **chains;  /**< The array of chains of symbols. */
} symtab;

extern symtab *idents;
extern symtab *symtab_new(int size);
extern symbol *symtab_store(symtab *table, char *name, int value);
extern symbol *symtab_find(symtab *table, char *name);

////////////////
// Parse Tree //
////////////////

/**
 * A Boolean constant expression.
 */
typedef struct {
  long value;  /**< The value of the constant. 0 = false and 1 = true; */
} bool_const_expr;

/**
 * An integer constant expression.
 */
typedef struct {
  long value;     /**< The value of the constant. */
} int_const_expr;

/**
 * A variable expression.
 */
typedef struct {
  symbol *symbol; /**< The symbol representing the variable. */
} variable_expr;

/**
 * Constants for binary operators.
 */
typedef enum {
  B_PLUS, B_MINUS, B_TIMES, B_DIV, B_MOD, B_AND, B_OR, B_LT, B_LE, B_EQ, B_NE, B_GE, B_GT
} binary_op;

/**
 * An expression.
 */
typedef struct expr expr;

/**
 * An expression with a binary operator.
 */
typedef struct {
  int op;      /**< The binary operator. */
  expr *lrand; /**< The left operand.    */
  expr *rrand; /**< The ight operand.    */
} binary_expr;

/**
 * Constants for unary operators.
 */
typedef enum {
  U_PLUS, U_MINUS, U_NOT, U_INCRE, U_DECRE
} unary_op;

/**
 * An expression with a unary operator.
 */
typedef struct {
  int op;      /**< The unary operator. */
  expr *rand;  /**< The operand.        */
} unary_expr;

/**
 * Constants for expression tags.
 */
typedef enum {
  E_BOOL_CONST, E_INT_CONST, E_VARIABLE, E_UNARY, E_BINARY
} expr_tag;

/**
 * An expression.
 */
struct expr {
  expr_tag tag;                   /**< Tagfield.         */
  union {
    bool_const_expr *bool_const;  /**< Boolean constant. */
    int_const_expr *int_const;    /**< Integer constant. */
    variable_expr *variable;      /**< Variable          */
    unary_expr *unary;            /**< Unary expression  */
    binary_expr *binary;          /**< Binary expression */
  };
};

/**
 * A statement.
 */
typedef struct stmt stmt;
  
/**
 * If statement.
 */
typedef struct {
  expr *test;  /**< The Boolean test.                                 */
  stmt *body;  /**< The statement to be executed if the test is true. */
} if_stmt;

/**
 * If Else statement.
 */
typedef struct {
  expr *test;       /**< The Boolean test.                                  */
  stmt *if_body;    /**< The statement to be executed if the test is true.  */
  stmt *else_body;  /**< The statement to be executed if the test is false. */
} if_else_stmt;

/**
 * while statement.
 */
typedef struct {
  expr *test;  /**< The Boolean test.                                    */
  stmt *body;  /**< The statement to be executed while the test is true. */
} while_stmt;

/**
 * Assigment statement.
 */
typedef struct {
  symbol *lvalue;  /**< The lvalue. */
  expr *rvalue;    /**< The rvalue. */
} assign_stmt;

typedef struct {
  expr *value;
} unary_stmt;
/**
 * Write statement.
 */
typedef struct {
  expr *arg;  /**< The argument. */
} write_stmt;

/**
 * Writeln statement.
 */
typedef struct {
} writeln_stmt;

/**
 * Empty statement.
 */
typedef struct {
} empty_stmt;

/**
 * Constants for statement tags.
 */
typedef enum {
  S_IF, S_IF_ELSE, S_WHILE, S_CMPD, S_ASSIGN, S_UNARY, S_WRITE, S_WRITELN, S_EMPTY
} stmt_tag;

/**
 * A compound statement.
 */
typedef struct cmpd_stmt cmpd_stmt;

/**
 * Statements.
 */
struct stmt {
  stmt_tag tag;             /**< Tagfield.             */
  union {
    if_stmt *iff;           /**< If statement.         */
    if_else_stmt *if_else;  /**< If statement.         */
    while_stmt *whilee;     /**< While statement.      */
    assign_stmt *assign;    /**< Assignment statement. */
    unary_stmt *sunary;
    write_stmt *write;      /**< Write statement.      */
    writeln_stmt *writeln;  /**< Writeln statement.    */
    cmpd_stmt *cmpd;        /**< Compound statement.   */
    empty_stmt *empty;      /**< Empty statement.      */
  };
};

/**
 * List of statements.
 */
typedef struct stmt_list {
  stmt *first;             /**< The first statement in the list.     */
  struct stmt_list *next;  /**< The remainder of the list.           */
} stmt_list;

/**
 * Compound statement.
 */
struct cmpd_stmt {
  stmt_list *stmts;  /**< A list of statements. */
};

/**
 * List of symbols.
 */
typedef struct symbol_list {
  symbol *first;             /**< The first symbol in the list. */
  struct symbol_list *next;  /**< The remainder of the list.    */
} symbol_list;

/**
 * Variable Declaration.
 */
typedef struct {
  symbol_list *variables;  /**< The variables that are being declared. */
  type type;               /**< The type of the variables.             */
} decl;

/**
 * List of declarations.
 */
typedef struct decl_list {  
  decl *first;             /**< The first declaratio in the list.  */
  struct decl_list *next;  /**< The remainder of the declarations. */
} decl_list;

/**
 * A program.
 */
typedef struct prog {
  symbol *name;     /**< The name of the program. */
  decl_list *decls; /**< The list of declarations. */
  stmt *body;  /**< The body of the program. */
} prog;

/**
 * The parse tree.
 */
extern prog *parse_tree;

extern bool_const_expr *bool_const_expr_new(long value);
extern int_const_expr *int_const_expr_new(long value);
extern variable_expr *variable_expr_new(symbol *symbol);
extern unary_expr *unary_expr_new(int op, expr *rand);
extern unary_stmt *unary_stmt_new(expr *value);
extern unary_expr *unary_expr_new(int op, expr *rand);
extern binary_expr *binary_expr_new(int op, expr *lrand, expr *rrand);
extern expr *expr_new(expr_tag tag, ... );
extern if_stmt *if_stmt_new(expr *test, stmt *body);
extern if_else_stmt *if_else_stmt_new(expr *test, stmt *if_body, stmt *else_body);
extern while_stmt *while_stmt_new(expr *test, stmt *body);
extern assign_stmt *assign_stmt_new(symbol *lvalue, expr *rvalue);
extern write_stmt *write_stmt_new(expr *arg);
extern writeln_stmt *writeln_stmt_new();
extern cmpd_stmt *cmpd_stmt_new(stmt_list *stmts);
extern empty_stmt *empty_stmt_new();
extern stmt *stmt_new(stmt_tag tag, ... );
extern stmt_list *stmt_list_new(stmt *first, stmt_list *next);
extern decl_list *decl_list_new(decl *first, decl_list *next);
extern symbol_list *symbol_list_new(symbol *first, symbol_list *next);
extern decl *decl_new(symbol_list *variables, type type);
extern prog *prog_new(symbol *name, decl_list *decls, stmt *body);
extern int prog_gen(prog *p);
extern void prog_print(prog *p);

/////////////
// Scanner //
/////////////

/**
 * Semantic values.
 */
typedef union {
  long int_const;                    /**< The value of an integer constant.          */
  symbol *symbol;                    /**< A pointer to the symbol for an identifier. */
  stmt_list *stmt_list;              /**< A statement list.                          */
  stmt *stmt;                        /**< A statement.                               */
  if_stmt *if_stmt;                  /**< An if statement.                           */
  if_else_stmt *if_else_stmt;        /**< An if statement.                           */
  while_stmt *while_stmt;            /**< A while statement.                         */
  assign_stmt *assign_stmt;          /**< An assignment statement.                   */
  unary_stmt *unary_stmt;
  write_stmt *write_stmt;            /**< A write statement.                         */
  writeln_stmt *writeln_stmt;        /**< A writeln statement.                       */
  cmpd_stmt *cmpd_stmt;              /**< A compound statement.                      */
  empty_stmt *empty_stmt;            /**< An empty statement.                        */
  expr* expr;                        /**< An expression.                             */
  bool_const_expr* bool_const_expr;  /**< A Boolean constant expression.             */
  int_const_expr* int_const_expr;    /**< An integer constant expression.            */
  variable_expr* variable_expr;      /**< A variable expression.                     */
  unary_expr* unary_expr;            /**< A unary expression.                        */
  binary_expr* binary_expr;          /**< A binary expression.                       */
  decl *decl;                        /**< A declaration.                             */
  decl_list *decl_list;              /**< A list of declarations.                    */
  symbol_list *symbol_list;          /**< A list of symbols.                         */
} YYSTYPE;

/**
 * The flex-generated scanner function.
 * @return A token code.
 */
extern int yylex();

/////////////////////
// Error reporting //
/////////////////////
extern void error(char *format, ... );
extern void yyerror(char *msg);

////////////
// Parser //
////////////

/**
 * The bison-generated parser function. The parser also does code generation.
 * @return 0 on success and a nonzero value on failure.
 */
extern int yyparse();

///////////////////
// Type Checking //
///////////////////

void declare_variables(prog *p);

///////////////////////
// Intermediate Code //
///////////////////////

/**
 * Opcodes.
 */
typedef enum opcode {
  O_ADD, O_SUB, O_MUL, O_DIV, O_MOD, O_ASSIGN, O_INT_CONST, O_WRITE, O_WRITELN,
  O_GOFALSE, O_GOTRUE, O_GOTO, O_NOOP, O_GOLT, O_GOLE, O_GOEQ, O_GONE, O_GOGT, O_GOGE,
  O_INCRE, O_DECRE, O_UPLUS, O_UMINUS, O_NOT
} opcode;

/**
 * A quadruple. Quadruples represent 3-address statements.
 */
typedef struct {
  opcode opcode;     /**< The opcode.                         */
  union  {
    long int_const;  /**< The left operand if it's immediate. */
    symbol *left;    /**< The left operand if it's symbol.    */
  };
  symbol *right;     /**< The right operand.                  */
  symbol *result;    /**< The result operand.                 */
} quad;

/**
 * The size of the quadruple table.
 */
#define MAX_QUADS 2048

extern quad quads[MAX_QUADS];

/////////////////
// The Emitter //
/////////////////

extern void emit_prologue();
extern void emit_epilogue();
extern void emit(int n);

#endif
