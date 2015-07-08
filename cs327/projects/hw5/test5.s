.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   T_2 = T_0 + T_1
    movq T_0, %r8
    movq T_1, %r9
    addq %r9, %r8
    movq %r8, T_2

#   T_3 = 0
    movq $0, %r9
    movq %r9, T_3

#   if T_2 = T_3 goto L_1
    movq T_2, %r8
    movq T_3, %r9
    cmpq %r9, %r8
    je L_1

    jmp L_0

L_1:

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   write T_4
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq $0, %rax
    call printf

L_0:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_5 = 1
    movq $1, %r9
    movq %r9, T_5

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   T_7 = T_5 + T_6
    movq T_5, %r8
    movq T_6, %r9
    addq %r9, %r8
    movq %r8, T_7

#   T_8 = 2
    movq $2, %r9
    movq %r9, T_8

#   if T_7 = T_8 goto L_3
    movq T_7, %r8
    movq T_8, %r9
    cmpq %r9, %r8
    je L_3

    jmp L_2

L_3:

#   T_9 = 2
    movq $2, %r9
    movq %r9, T_9

#   write T_9
    movq $writefmt, %rdi
    movq T_9, %rsi
    movq $0, %rax
    call printf

L_2:

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

test5:	.quad 0
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
