.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = 10
    movq $10, %r9
    movq %r9, T_1

#   i = T_0
    movq T_0, %r9
    movq %r9, i

L_0:

#   if T_1 less than i goto L_1
    movq i, %r8
    movq T_1, %r9
    cmpq %r8, %r9
    jl L_1

#   T_2 = 2
    movq $2, %r9
    movq %r9, T_2

#   T_3 = 2
    movq $2, %r9
    movq %r9, T_3

#   T_4 = i / T_3
    movq i, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq T_3, %r9
    idivq %r9
    movq %rax, T_4

#   T_5 = T_2 * T_4
    movq T_2, %r8
    movq T_4, %r9
    imulq %r9, %r8
    movq %r8, T_5

#   T_6 = T_5 - i
    movq T_5, %r8
    movq i, %r9
    subq %r9, %r8
    movq %r8, T_6

#   ifnot T_6 goto L_2
    movq T_6, %r8
    testq %r8, %r8
    jz L_2

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

L_2:

#   i = i + 1
    movq i, %r8
    addq $1, %r8
    movq %r8, i
#   goto L_0
    jmp L_0

L_1:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

i:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
