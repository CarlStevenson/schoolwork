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

#   write T_3
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   T_5 = 2
    movq $2, %r9
    movq %r9, T_5

#   T_6 = T_4 - T_5
    movq T_4, %r8
    movq T_5, %r9
    subq %r9, %r8
    movq %r8, T_6

#   write T_6
    movq $writefmt, %rdi
    movq T_6, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_7 = 1
    movq $1, %r9
    movq %r9, T_7

#   a = T_7
    movq T_7, %r9
    movq %r9, a

#   T_8 = 4
    movq $4, %r9
    movq %r9, T_8

    movq a, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_9
#   T_10 = T_8 + T_9
    movq T_8, %r8
    movq T_9, %r9
    addq %r9, %r8
    movq %r8, T_10

#   write T_10
    movq $writefmt, %rdi
    movq T_10, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_11 = 4
    movq $4, %r9
    movq %r9, T_11

#   T_12 = T_11 + a
    movq T_11, %r8
    movq a, %r9
    addq %r9, %r8
    movq %r8, T_12

#   write T_12
    movq $writefmt, %rdi
    movq T_12, %rsi
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

testUnary2:	.quad 0
a:	.quad 0
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
