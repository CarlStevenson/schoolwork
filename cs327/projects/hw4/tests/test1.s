.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = 2
    movq $2, %r9
    movq %r9, T_1

#   T_2 = T_0 + T_1
    movq T_0, %r8
    movq T_1, %r9
    addq %r9, %r8
    movq %r8, T_2

#   write T_2
    movq $writefmt, %rdi
    movq T_2, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_3 = 1
    movq $1, %r9
    movq %r9, T_3

#   T_4 = 2
    movq $2, %r9
    movq %r9, T_4

#   T_5 = T_3 - T_4
    movq T_3, %r8
    movq T_4, %r9
    subq %r9, %r8
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

#   T_6 = 4
    movq $4, %r9
    movq %r9, T_6

#   T_7 = 7
    movq $7, %r9
    movq %r9, T_7

#   T_8 = T_6 * T_7
    movq T_6, %r8
    movq T_7, %r9
    imulq %r9, %r8
    movq %r8, T_8

#   write T_8
    movq $writefmt, %rdi
    movq T_8, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_9 = 12
    movq $12, %r9
    movq %r9, T_9

#   T_10 = 3
    movq $3, %r9
    movq %r9, T_10

#   T_11 = T_9 / T_10
    movq T_9, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq T_10, %r9
    idivq %r9
    movq %rax, T_11

#   write T_11
    movq $writefmt, %rdi
    movq T_11, %rsi
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
