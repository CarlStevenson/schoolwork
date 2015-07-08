.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   a = T_0
    movq T_0, %r9
    movq %r9, a

#   T_1 = 2
    movq $2, %r9
    movq %r9, T_1

#   b = T_1
    movq T_1, %r9
    movq %r9, b

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   write b
    movq $writefmt, %rdi
    movq b, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq a, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, a
    movq %r9, T_2
    movq a, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, a
    movq %r9, T_3
#   T_4 = T_2 + T_3
    movq T_2, %r8
    movq T_3, %r9
    addq %r9, %r8
    movq %r8, T_4

    movq a, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, a
    movq %r8, T_5
#   T_6 = T_4 + T_5
    movq T_4, %r8
    movq T_5, %r9
    addq %r9, %r8
    movq %r8, T_6

    movq a, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, a
    movq %r8, T_7
#   T_8 = T_6 - T_7
    movq T_6, %r8
    movq T_7, %r9
    subq %r9, %r8
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

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   write b
    movq $writefmt, %rdi
    movq b, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq b, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, b
    movq %r9, T_9
    movq b, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, b
    movq %r9, T_10
#   T_11 = T_9 - T_10
    movq T_9, %r8
    movq T_10, %r9
    subq %r9, %r8
    movq %r8, T_11

    movq b, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, b
    movq %r8, T_12
#   T_13 = T_11 - T_12
    movq T_11, %r8
    movq T_12, %r9
    subq %r9, %r8
    movq %r8, T_13

    movq b, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, b
    movq %r8, T_14
#   T_15 = T_13 + T_14
    movq T_13, %r8
    movq T_14, %r9
    addq %r9, %r8
    movq %r8, T_15

#   write T_15
    movq $writefmt, %rdi
    movq T_15, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   write b
    movq $writefmt, %rdi
    movq b, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq a, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, a
    movq %r9, T_16
    movq b, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, b
    movq %r9, T_17
#   T_18 = T_16 - T_17
    movq T_16, %r8
    movq T_17, %r9
    subq %r9, %r8
    movq %r8, T_18

    movq b, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, b
    movq %r8, T_19
#   T_20 = T_18 - T_19
    movq T_18, %r8
    movq T_19, %r9
    subq %r9, %r8
    movq %r8, T_20

    movq a, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, a
    movq %r8, T_21
#   T_22 = T_20 + T_21
    movq T_20, %r8
    movq T_21, %r9
    addq %r9, %r8
    movq %r8, T_22

#   write T_22
    movq $writefmt, %rdi
    movq T_22, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

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
testIncr3:	.quad 0
T_10:	.quad 0
T_20:	.quad 0
T_11:	.quad 0
T_21:	.quad 0
T_12:	.quad 0
T_22:	.quad 0
T_13:	.quad 0
T_14:	.quad 0
T_15:	.quad 0
T_16:	.quad 0
T_17:	.quad 0
T_18:	.quad 0
T_19:	.quad 0
