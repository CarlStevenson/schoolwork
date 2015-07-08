.section .text

.globl main
main: enter $0, $0

#   T_0 = 10
    movq $10, %r9
    movq %r9, T_0

#   x = T_0
    movq T_0, %r9
    movq %r9, x

L_0:

#   ifnot x goto L_1
    movq x, %r8
    testq %r8, %r8
    jz L_1

#   write x
    movq $writefmt, %rdi
    movq x, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   T_2 = x - T_1
    movq x, %r8
    movq T_1, %r9
    subq %r9, %r8
    movq %r8, T_2

#   x = T_2
    movq T_2, %r9
    movq %r9, x

#   goto L_0
    jmp L_0

L_1:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

x:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
