.section .text

.globl main
main: enter $0, $0

    jmp L_3

L_3:

    jmp L_1

L_1:

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   write T_0
    movq $writefmt, %rdi
    movq T_0, %rsi
    movq $0, %rax
    call printf

    jmp L_0

L_2:

#   T_1 = 2
    movq $2, %r9
    movq %r9, T_1

#   write T_1
    movq $writefmt, %rdi
    movq T_1, %rsi
    movq $0, %rax
    call printf

L_0:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_6

L_7:

    jmp L_5

L_5:

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   write T_2
    movq $writefmt, %rdi
    movq T_2, %rsi
    movq $0, %rax
    call printf

    jmp L_4

L_6:

#   T_3 = 2
    movq $2, %r9
    movq %r9, T_3

#   write T_3
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq $0, %rax
    call printf

L_4:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_11

L_11:

    jmp L_10

L_9:

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   write T_4
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq $0, %rax
    call printf

    jmp L_8

L_10:

#   T_5 = 2
    movq $2, %r9
    movq %r9, T_5

#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq $0, %rax
    call printf

L_8:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_14

L_15:

    jmp L_14

L_13:

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   write T_6
    movq $writefmt, %rdi
    movq T_6, %rsi
    movq $0, %rax
    call printf

    jmp L_12

L_14:

#   T_7 = 2
    movq $2, %r9
    movq %r9, T_7

#   write T_7
    movq $writefmt, %rdi
    movq T_7, %rsi
    movq $0, %rax
    call printf

L_12:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_17

L_19:

    jmp L_17

L_17:

#   T_8 = 1
    movq $1, %r9
    movq %r9, T_8

#   write T_8
    movq $writefmt, %rdi
    movq T_8, %rsi
    movq $0, %rax
    call printf

    jmp L_16

L_18:

#   T_9 = 2
    movq $2, %r9
    movq %r9, T_9

#   write T_9
    movq $writefmt, %rdi
    movq T_9, %rsi
    movq $0, %rax
    call printf

L_16:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_23

L_23:

    jmp L_21

L_21:

#   T_10 = 1
    movq $1, %r9
    movq %r9, T_10

#   write T_10
    movq $writefmt, %rdi
    movq T_10, %rsi
    movq $0, %rax
    call printf

    jmp L_20

L_22:

#   T_11 = 2
    movq $2, %r9
    movq %r9, T_11

#   write T_11
    movq $writefmt, %rdi
    movq T_11, %rsi
    movq $0, %rax
    call printf

L_20:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_25

L_27:

    jmp L_26

L_25:

#   T_12 = 1
    movq $1, %r9
    movq %r9, T_12

#   write T_12
    movq $writefmt, %rdi
    movq T_12, %rsi
    movq $0, %rax
    call printf

    jmp L_24

L_26:

#   T_13 = 2
    movq $2, %r9
    movq %r9, T_13

#   write T_13
    movq $writefmt, %rdi
    movq T_13, %rsi
    movq $0, %rax
    call printf

L_24:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_31

L_31:

    jmp L_30

L_29:

#   T_14 = 1
    movq $1, %r9
    movq %r9, T_14

#   write T_14
    movq $writefmt, %rdi
    movq T_14, %rsi
    movq $0, %rax
    call printf

    jmp L_28

L_30:

#   T_15 = 2
    movq $2, %r9
    movq %r9, T_15

#   write T_15
    movq $writefmt, %rdi
    movq T_15, %rsi
    movq $0, %rax
    call printf

L_28:

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

test2:	.quad 0
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
