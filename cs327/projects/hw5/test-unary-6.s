.section .text

.globl main
main: enter $0, $0

#   T_0 = 0
    movq $0, %r9
    movq %r9, T_0

#   i = T_0
    movq T_0, %r9
    movq %r9, i

L_1:

#   T_1 = 10
    movq $10, %r9
    movq %r9, T_1

#   if i < T_1 goto L_2
    movq i, %r8
    movq T_1, %r9
    cmpq %r9, %r8
    jl L_2

    jmp L_0

L_2:

#   T_2 = 2
    movq $2, %r9
    movq %r9, T_2

#   T_3 = i / T_2
    movq i, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq T_2, %r9
    idivq %r9
    movq %rdx, T_3

#   T_4 = 0
    movq $0, %r9
    movq %r9, T_4

#   if T_3 = T_4 goto L_5
    movq T_3, %r8
    movq T_4, %r9
    cmpq %r9, %r8
    je L_5

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_3

L_5:

    movq i, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_5
#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

L_3:

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   T_7 = i + T_6
    movq i, %r8
    movq T_6, %r9
    addq %r9, %r8
    movq %r8, T_7

#   i = T_7
    movq T_7, %r9
    movq %r9, i

    jmp L_1

L_0:

#   T_8 = 10
    movq $10, %r9
    movq %r9, T_8

#   i = T_8
    movq T_8, %r9
    movq %r9, i

L_7:

#   T_9 = 0
    movq $0, %r9
    movq %r9, T_9

#   if i = T_9 goto L_6
    movq i, %r8
    movq T_9, %r9
    cmpq %r9, %r8
    je L_6

#   T_10 = 2
    movq $2, %r9
    movq %r9, T_10

#   T_11 = i / T_10
    movq i, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq T_10, %r9
    idivq %r9
    movq %rdx, T_11

#   T_12 = 0
    movq $0, %r9
    movq %r9, T_12

#   if T_11 = T_12 goto L_11
    movq T_11, %r8
    movq T_12, %r9
    cmpq %r9, %r8
    je L_11

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_9

L_11:

    movq i, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_13
#   write T_13
    movq $writefmt, %rdi
    movq T_13, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

L_9:

#   T_14 = 1
    movq $1, %r9
    movq %r9, T_14

#   T_15 = i - T_14
    movq i, %r8
    movq T_14, %r9
    subq %r9, %r8
    movq %r8, T_15

#   i = T_15
    movq T_15, %r9
    movq %r9, i

    jmp L_7

L_6:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

testUnary6:	.quad 0
a:	.quad 0
b:	.quad 0
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
T_10:	.quad 0
T_11:	.quad 0
T_12:	.quad 0
T_13:	.quad 0
T_14:	.quad 0
T_15:	.quad 0
