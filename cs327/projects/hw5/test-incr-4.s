.section .text

.globl main
main: enter $0, $0

#   T_0 = 0
    movq $0, %r9
    movq %r9, T_0

#   a = T_0
    movq T_0, %r9
    movq %r9, a

L_1:

    movq a, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, a
    movq %r9, T_1
#   T_2 = 10
    movq $10, %r9
    movq %r9, T_2

#   if T_1 <= T_2 goto L_2
    movq T_1, %r8
    movq T_2, %r9
    cmpq %r9, %r8
    jle L_2

    jmp L_0

L_2:

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_1

L_0:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

a:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
testIncr4:	.quad 0
