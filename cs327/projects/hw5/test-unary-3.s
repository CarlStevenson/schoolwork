.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

    movq T_0, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_1
#   T_2 = 2
    movq $2, %r9
    movq %r9, T_2

#   T_3 = T_1 + T_2
    movq T_1, %r8
    movq T_2, %r9
    addq %r9, %r8
    movq %r8, T_3

#   T_4 = 3
    movq $3, %r9
    movq %r9, T_4

    movq T_4, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_5
#   T_6 = T_3 - T_5
    movq T_3, %r8
    movq T_5, %r9
    subq %r9, %r8
    movq %r8, T_6

#   a = T_6
    movq T_6, %r9
    movq %r9, a

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq a, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_7
    movq a, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_8
#   T_9 = T_7 - T_8
    movq T_7, %r8
    movq T_8, %r9
    subq %r9, %r8
    movq %r8, T_9

    movq a, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_10
#   T_11 = a * T_10
    movq a, %r8
    movq T_10, %r9
    imulq %r9, %r8
    movq %r8, T_11

#   T_12 = T_9 + T_11
    movq T_9, %r8
    movq T_11, %r9
    addq %r9, %r8
    movq %r8, T_12

#   T_13 = a + a
    movq a, %r8
    movq a, %r9
    addq %r9, %r8
    movq %r8, T_13

    movq T_13, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_14
#   T_15 = T_12 + T_14
    movq T_12, %r8
    movq T_14, %r9
    addq %r9, %r8
    movq %r8, T_15

#   b = T_15
    movq T_15, %r9
    movq %r9, b

#   write b
    movq $writefmt, %rdi
    movq b, %rsi
    movq $0, %rax
    call printf

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

testUnary3:	.quad 0
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
T_8:	.quad 0
T_9:	.quad 0
T_10:	.quad 0
T_11:	.quad 0
T_12:	.quad 0
T_13:	.quad 0
T_14:	.quad 0
T_15:	.quad 0
