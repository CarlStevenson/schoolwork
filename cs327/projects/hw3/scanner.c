/** @file scanner.c
 * The lexical analyzer for the stack compiler.
 */

#include <stdio.h>
#include <ctype.h>
#include "globals.h"

/**  
 * A global variable that stores the value of the token in lookahead.
 */
union lexical_value token_value;

/**
 * The next character in the input stream.
 */
static int lookahead = EOF + 1;

/**
 * A global array that stores strings that provide printable descriptions of tokens.
 * These names must be in one-to-one correspondence with the token constants.
 */
char *token_names[] = {
  "EOF", "WRITE", "WRITELN", "PLUS", "MINUS", "TIMES", "DIV", "IDENT", "CONST", 
  "LPAREN", "RPAREN", "ASSIGN", "SEMI", "IF", "THEN", "WHILE", "DO"
};

/**
 * Convert a character digit to its integer value.
 */
#define char_to_int(d) (d - '0')

/**
 * Scan an identifier. The next character in the input stream
 * must be a letter.
 */
int scan_ident() 
{
  // The lexeme.
  char lexeme[MAX_IDENT_LENGTH + 1];

  // Store the first character.
  lexeme[0] = lookahead;

  // Prepare to store the next character.
  int position = 1;

  // Read characters and store them as long as we're seeing 
  // letters or digits.
  while (1) {
    // Get the next character.
    lookahead = getchar();

    // Check for letters and digits.
    if (isalpha(lookahead) || isdigit(lookahead)) {
      // Check to see if we're about exceed the array size.
      if (position > MAX_IDENT_LENGTH) {
	error("Maximum identifier length exceeded\n");
      }
      // Store the character and increment the position.
      lexeme[position] = lookahead;
      position++;
    }
    else {
      // We're no longer reading an identifier.
      // Mark the end of the string.
      lexeme[position] = 0;

      // Put the last character back.
      ungetc(lookahead, stdin);

      // Look up the lexeme in the symbol tables.
      struct symbol *sym = symtab_find(reserved, lexeme);
      if (sym == NULL) {
	// It's not a reserved word, so look it up in the
	// identifier table.
	sym = symtab_find(idents, lexeme);
	if (sym == NULL) {
	  // It's an identifier we haven't seen before, so
	  // store it.
	  sym = symtab_store(idents, lexeme, 0);
	}
	// Set the token value.
	token_value.symbol_value = sym;
	return T_IDENT;
      }
      else {
	// It's a reserved word, so return the token code.
	return sym->value;
      }
    }
  }
}

/**
 * Scan an integer constant. The next character in the input
 * stream must be a digit.
 */
int scan_int_const() 
{
  // Initialize the first character to an integer.
  long value = char_to_int(lookahead);

  // Read characters as long as we're seeing digits.
  while (1) {
    // Read the next character.
    lookahead = getchar();

    // Check for digits.
    if (isdigit(lookahead)) {

      // Convert the character to an integer, shift the value
      // to the left by one position, and add in the digit
      // we just read.
      value = value * 10 + char_to_int(lookahead);
    }
    else {
      // We did not find a digit.
      // Put the character back.
      ungetc(lookahead, stdin);

      // We're done. Store the value and return.
      token_value.const_value = value;
      return T_CONST;
    }
  }
}

/**
 * The lexical analyzer function. Values for tokens (those that have values) are
 * stored in global variables.
 * @return The code for a token.
 */
int scan() 
{
  // Read characters until we have read a complete token.
  if (lookahead == EOF) {
    return T_EOF;
  }
  while (1) {
    // Read the next character.
    lookahead = getchar();

    // Skip whitespace.
    if (isspace(lookahead)) continue;

    // Check for letters.
    if (isalpha(lookahead)) {
      // Scan an identifier.
      return scan_ident();
    }

    // Check for digits.
    if (isdigit(lookahead)) {
      // Scan an integer constant.
      return scan_int_const();
    }

    // Check for end of file, reserved words, and punctuation.
    switch (lookahead) {
    case EOF: return T_EOF;
    case '+': return T_PLUS;
    case '-': return T_MINUS;
    case '*': return T_TIMES;
    case '/': return T_DIV;
    case '(': return T_LPAREN;
    case ')': return T_RPAREN;
    case ';': return T_SEMI;
    case ':': 
      // An assignment & comparator token
      lookahead = getchar();
      if(lookahead == '=') return T_ASSIGN;
      
      else error("Invalid character: %c", lookahead);
    default: 
      // We found a bad character, so print a message
      // and quit.
      error("Invalid character: %c", lookahead); 
    }
  }
}
