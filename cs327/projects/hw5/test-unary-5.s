.section .text

.globl main
main: enter $0, $0

#   a = 1
    movq $1, %r9
    movq %r9, a

    jmp L_2

#   a = 0
    movq $0, %r9
    movq %r9, a

L_2:

#   if a goto L_5
    movq a, %r8
    testq %r8, %r8
    jnz L_5

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   write T_0
    movq $writefmt, %rdi
    movq T_0, %rsi
    movq $0, %rax
    call printf

    jmp L_3

L_5:

#   T_1 = 2
    movq $2, %r9
    movq %r9, T_1

#   write T_1
    movq $writefmt, %rdi
    movq T_1, %rsi
    movq $0, %rax
    call printf

L_3:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   if a goto L_9
    movq a, %r8
    testq %r8, %r8
    jnz L_9

    jmp L_8

L_9:

    jmp L_8

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   write T_2
    movq $writefmt, %rdi
    movq T_2, %rsi
    movq $0, %rax
    call printf

    jmp L_6

L_8:

#   T_3 = 2
    movq $2, %r9
    movq %r9, T_3

#   write T_3
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq $0, %rax
    call printf

L_6:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   if a goto L_11
    movq a, %r8
    testq %r8, %r8
    jnz L_11

    jmp L_12

L_11:

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   write T_4
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq $0, %rax
    call printf

    jmp L_10

L_12:

#   T_5 = 2
    movq $2, %r9
    movq %r9, T_5

#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq $0, %rax
    call printf

L_10:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_15

#   b = 1
    movq $1, %r9
    movq %r9, b

    jmp L_16

L_15:

#   b = 0
    movq $0, %r9
    movq %r9, b

L_16:

#   if a goto L_20
    movq a, %r8
    testq %r8, %r8
    jnz L_20

    jmp L_19

L_20:

#   if b goto L_18
    movq b, %r8
    testq %r8, %r8
    jnz L_18

    jmp L_19

L_18:

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   write T_6
    movq $writefmt, %rdi
    movq T_6, %rsi
    movq $0, %rax
    call printf

    jmp L_17

L_19:

#   T_7 = 2
    movq $2, %r9
    movq %r9, T_7

#   write T_7
    movq $writefmt, %rdi
    movq T_7, %rsi
    movq $0, %rax
    call printf

L_17:

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

testUnary5:	.quad 0
a:	.quad 0
b:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
T_7:	.quad 0
