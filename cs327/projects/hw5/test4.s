.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   i = T_0
    movq T_0, %r9
    movq %r9, i

L_1:

#   T_1 = 10
    movq $10, %r9
    movq %r9, T_1

#   if i <= T_1 goto L_2
    movq i, %r8
    movq T_1, %r9
    cmpq %r9, %r8
    jle L_2

    jmp L_0

L_2:

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   j = T_2
    movq T_2, %r9
    movq %r9, j

L_4:

#   if j <= i goto L_5
    movq j, %r8
    movq i, %r9
    cmpq %r9, %r8
    jle L_5

    jmp L_3

L_5:

#   T_3 = 2
    movq $2, %r9
    movq %r9, T_3

#   T_4 = j / T_3
    movq j, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq T_3, %r9
    idivq %r9
    movq %rdx, T_4

#   T_5 = 0
    movq $0, %r9
    movq %r9, T_5

#   if T_4 = T_5 goto L_7
    movq T_4, %r8
    movq T_5, %r9
    cmpq %r9, %r8
    je L_7

    jmp L_6

L_7:

#   write j
    movq $writefmt, %rdi
    movq j, %rsi
    movq $0, %rax
    call printf

L_6:

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   T_7 = j + T_6
    movq j, %r8
    movq T_6, %r9
    addq %r9, %r8
    movq %r8, T_7

#   j = T_7
    movq T_7, %r9
    movq %r9, j

    jmp L_4

L_3:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_8 = 1
    movq $1, %r9
    movq %r9, T_8

#   T_9 = i + T_8
    movq i, %r8
    movq T_8, %r9
    addq %r9, %r8
    movq %r8, T_9

#   i = T_9
    movq T_9, %r9
    movq %r9, i

    jmp L_1

L_0:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

foo:	.quad 0
i:	.quad 0
j:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
T_7:	.quad 0
T_8:	.quad 0
T_9:	.quad 0
loops:	.quad 0
