/** @file
 * Emitter functions.
 */

#include <stdio.h>
#include "globals.h"

/**
 * Emit an add instruction.
 * @param i The number of the quad to emit.
 */
void emit_add(int i) {
  printf("#   %s = %s + %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    addq %%r9, %%r8\n");
  printf("    movq %%r8, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a sub instruction.
 * @param i The number of the quad to emit.
 */
void emit_sub(int i) {
  printf("#   %s = %s - %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    subq %%r9, %%r8\n");
  printf("    movq %%r8, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a mul instruction.
 * @param i The number of the quad to emit.
 */
void emit_mul(int i) {
  printf("#   %s = %s * %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    imulq %%r9, %%r8\n");
  printf("    movq %%r8, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a div instruction.
 * @param i The number of the quad to emit.
 */
void emit_div(int i) {
  printf("#   %s = %s / %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name);
  printf("    movq %s, %%rax\n", quads[i].left->name);
  printf("    movq %%rax, %%rdx\n");
  printf("    sarq $63, %%rdx\n");
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    idivq %%r9\n");
  printf("    movq %%rax, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a mod instruction.
 * @param i The number of the quad to emit.
 */
void emit_mod(int i) {
  printf("#   %s = %s / %s\n", quads[i].result->name, quads[i].left->name, quads[i].right->name);
  printf("    movq %s, %%rax\n", quads[i].left->name);
  printf("    movq %%rax, %%rdx\n");
  printf("    sarq $63, %%rdx\n");
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    idivq %%r9\n");
  printf("    movq %%rdx, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a golt instruction.
 * @param i The number of the quad to emit.
 */
void emit_golt(int i) {
  printf("#   if %s < %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    jl %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a gole instruction.
 * @param i The number of the quad to emit.
 */
void emit_gole(int i) {
  printf("#   if %s <= %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    jle %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a goeq instruction.
 * @param i The number of the quad to emit.
 */
void emit_goeq(int i) {
  printf("#   if %s = %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    je %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a gone instruction.
 * @param i The number of the quad to emit.
 */
void emit_gone(int i) {
  printf("#   if %s <> %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    jne %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a gogt instruction.
 * @param i The number of the quad to emit.
 */
void emit_gogt(int i) {
  printf("#   if %s > %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    jg %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a goge instruction.
 * @param i The number of the quad to emit.
 */
void emit_goge(int i) {
  printf("#   if %s >= %s goto %s\n", quads[i].left->name, quads[i].right->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].right->name);
  printf("    cmpq %%r9, %%r8\n");
  printf("    jge %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit an assign instruction.
 * @param i The number of the quad to emit.
 */
void emit_assign(int i) {
  printf("#   %s = %s\n", quads[i].result->name, quads[i].left->name);
  printf("    movq %s, %%r9\n", quads[i].left->name);
  printf("    movq %%r9, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit an int_const instruction.
 * @param i The number of the quad to emit.
 */
void emit_int_const(int i) {
  printf("#   %s = %ld\n", quads[i].result->name, quads[i].int_const);
  printf("    movq $%ld, %%r9\n", quads[i].int_const);
  printf("    movq %%r9, %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a write instruction.
 * @param i The number of the quad to emit.
 */
void emit_write(int i) {
  printf("#   write %s\n", quads[i].left->name);
  printf("    movq $writefmt, %%rdi\n");
  printf("    movq %s, %%rsi\n", quads[i].left->name);
  printf("    movq $0, %%rax\n");
  printf("    call printf\n");
  printf("\n");
}

/**
 * Emit a writeln instruction.
 * @param i The number of the quad to emit.
 */
void emit_writeln(int i) {
  printf("#   writeln\n");
  printf("    movq $writelnfmt, %%rdi\n");
  printf("    movq $0, %%rax\n");
  printf("    call printf\n");
  printf("\n");
}

/**
 * Emit a gotrue instruction.
 * @param i The number of the quad to emit.
 */
void emit_gotrue(int i) {
  printf("#   if %s goto %s\n", quads[i].left->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    testq %%r8, %%r8\n");
  printf("    jnz %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a gofalse instruction.
 * @param i The number of the quad to emit.
 */
void emit_gofalse(int i) {
  printf("#   ifnot %s goto %s\n", quads[i].left->name, quads[i].result->name);
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    testq %%r8, %%r8\n");
  printf("    jz %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a goto instruction.
 * @param i The number of the quad to emit.
 */
void emit_goto(int i) {
  if (quads[i].result == NULL) return;
  printf("    jmp %s\n", quads[i].result->name);
  printf("\n");
}

/**
 * Emit a noop (label) instruction.
 */
void emit_noop(int i) {
  //  if (quads[i].left->value) {
    printf("%s:\n", quads[i].left->name);
    printf("\n");
    //  }
}

/**
 * Emit a increment instruction.
 */
void emit_incre(int i) {
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq $1, %%r9\n");
  printf("    addq %%r8, %%r9\n");
  printf("    movq %%r9, %s\n", quads[i].left->name);
  printf("    movq %%r9, %s\n", quads[i].result->name);
}

/**
 * Emit a decrement instruction.
 */
void emit_decre(int i) {
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq $1, %%r9\n");
  printf("    subq %%r9, %%r8\n");
  printf("    movq %%r9, %s\n", quads[i].left->name);
  printf("    movq %%r8, %s\n", quads[i].result->name);
}

/**
 * Emit a unary plus instruction.
 */
void emit_uplus(int i) {
  printf("    movq %s, %%rax\n", quads[i].left->name);
  printf("    movq %%rax, %%rdx\n");
  printf("    movq $1, %%r8\n");
  printf("    mul %%r8\n");
  printf("    movq %%rax, %s\n", quads[i].result->name);
}

/**
 * Emit a unary minus instruction.
 */
void emit_uminus(int i) {
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    movq $-1, %%r9\n");
  printf("    imul %%r9, %%r8\n");
  printf("    movq %%r8, %s\n", quads[i].result->name);
}

/**
 * Emit a negation instruction.
 */
void emit_not(int i) {
  printf("    movq %s, %%r8\n", quads[i].left->name);
  printf("    negq %%r8\n");
  printf("    movq %%r8, %s\n", quads[i].result->name);
  
}
/**
 * Emit the code for the beginning of an assembly program.
 */
void emit_prologue() {
  // Text section declaration.
  printf(".section .text\n");
  printf("\n");

  // Declare main.
  printf(".globl main\n");
  printf("main: enter $0, $0\n");
  printf("\n");
}

/** 
 * Emit the code for the end of an assembly program.
 */
void emit_epilogue() {
  // Set up to return 0.
  printf("\n");
  printf("  movq $0, %%rax\n");

  // Return from the function.
  printf("\n");
  printf("  leave\n");
  printf("  ret\n");
  printf("\n");

  // Format strings.
  printf(".section .rodata\n");
  printf("\n");
  printf("writefmt: .string \"%%8ld\"\n");
  printf("writelnfmt: .string \"\\n\"\n");
  printf("\n");

  // Traverse the symbol table to generate variable declarations.
  printf(".section .data\n");
  printf("\n");
  int i;
  for (i = 0; i < idents->size; i++) {
    struct symbol *s = idents->chains[i];
    while (s) {
      if (s->name[0] != 'L' || s->name[1] != '_')
	printf("%s:\t.quad 0\n", s->name);
      s = s->next;
    }
  }
}
  
/**
 * Emit amd64 code.
 * @param n The number of instructions to emit.
 */
void emit(int n) {
  int i;
  for (i = 0; i < n; i++) {
    switch (quads[i].opcode) {
    case O_ADD: emit_add(i); break;
    case O_SUB: emit_sub(i); break;
    case O_MUL: emit_mul(i); break;
    case O_DIV: emit_div(i); break;
    case O_MOD: emit_mod(i); break;
    case O_ASSIGN: emit_assign(i); break;
    case O_INT_CONST: emit_int_const(i); break;
    case O_WRITE: emit_write(i); break;
    case O_WRITELN: emit_writeln(i); break;
    case O_GOTRUE: emit_gotrue(i); break;
    case O_GOFALSE: emit_gofalse(i); break;
    case O_GOTO: emit_goto(i); break;
    case O_NOOP: emit_noop(i); break;
    case O_GOLT: emit_golt(i); break;
    case O_GOLE: emit_gole(i); break;
    case O_GOEQ: emit_goeq(i); break;
    case O_GONE: emit_gone(i); break;
    case O_GOGE: emit_goge(i); break;
    case O_GOGT: emit_gogt(i); break;
    case O_UPLUS: emit_uplus(i); break;
    case O_UMINUS: emit_uminus(i); break;
    case O_NOT: emit_not(i); break;
    case O_INCRE: emit_incre(i); break;
    case O_DECRE: emit_decre(i); break;
    }
  }
}
