/** @file emitter.c
 * Functions that emit stack instructions.
 */

#include <stdio.h>
#include <stdarg.h>
#include "globals.h"

/**
 * Emit an instruction.
 * @param opcode The opcode for the instruction to emit.
 * @param ... Operands for the instruction.
 */
void emit(int opcode, ... ) 
{
  va_list args;
  va_start(args, opcode);
  switch(opcode) {
  case O_ADD: printf("add"); break;
  case O_SUB: printf("sub"); break;
  case O_MUL: printf("mul"); break;
  case O_DIV: printf("div"); break;
  case O_PUSH: printf("push %ld", va_arg(args, long)); break;
  case O_LVALUE: printf("lvalue %s", va_arg(args, struct symbol *)->name); break;
  case O_RVALUE: printf("rvalue %s", va_arg(args, struct symbol *)->name); break;
  case O_ASSIGN: printf("assign"); break;
  case O_WRITE: printf("write"); break;
  case O_WRITELN: printf("writeln"); break;
  case O_GOFALSE: printf("gofalse %s", va_arg(args, char*)); break;
  case O_LABEL: printf("label %s", va_arg(args, char*)); break;
  case O_GOTO: printf("goto %s", va_arg(args, char*)); break;
  
  }
  va_end(args);
  printf("\n");
}
