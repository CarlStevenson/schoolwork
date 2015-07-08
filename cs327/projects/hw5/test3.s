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

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   T_3 = i + T_2
    movq i, %r8
    movq T_2, %r9
    addq %r9, %r8
    movq %r8, T_3

#   i = T_3
    movq T_3, %r9
    movq %r9, i

    jmp L_1

L_0:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   i = T_4
    movq T_4, %r9
    movq %r9, i

L_4:

#   T_5 = 10
    movq $10, %r9
    movq %r9, T_5

#   if i < T_5 goto L_5
    movq i, %r8
    movq T_5, %r9
    cmpq %r9, %r8
    jl L_5

    jmp L_3

L_5:

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

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

    jmp L_4

L_3:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_8 = 1
    movq $1, %r9
    movq %r9, T_8

#   i = T_8
    movq T_8, %r9
    movq %r9, i

L_7:

#   T_9 = 10
    movq $10, %r9
    movq %r9, T_9

#   if T_9 >= i goto L_8
    movq T_9, %r8
    movq i, %r9
    cmpq %r9, %r8
    jge L_8

    jmp L_6

L_8:

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   T_10 = 1
    movq $1, %r9
    movq %r9, T_10

#   T_11 = i + T_10
    movq i, %r8
    movq T_10, %r9
    addq %r9, %r8
    movq %r8, T_11

#   i = T_11
    movq T_11, %r9
    movq %r9, i

    jmp L_7

L_6:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_12 = 1
    movq $1, %r9
    movq %r9, T_12

#   i = T_12
    movq T_12, %r9
    movq %r9, i

L_10:

#   T_13 = 10
    movq $10, %r9
    movq %r9, T_13

#   if T_13 > i goto L_11
    movq T_13, %r8
    movq i, %r9
    cmpq %r9, %r8
    jg L_11

    jmp L_9

L_11:

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   T_14 = 1
    movq $1, %r9
    movq %r9, T_14

#   T_15 = i + T_14
    movq i, %r8
    movq T_14, %r9
    addq %r9, %r8
    movq %r8, T_15

#   i = T_15
    movq T_15, %r9
    movq %r9, i

    jmp L_10

L_9:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_16 = 1
    movq $1, %r9
    movq %r9, T_16

#   i = T_16
    movq T_16, %r9
    movq %r9, i

L_13:

#   T_17 = 10
    movq $10, %r9
    movq %r9, T_17

#   if i <> T_17 goto L_14
    movq i, %r8
    movq T_17, %r9
    cmpq %r9, %r8
    jne L_14

    jmp L_12

L_14:

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   T_18 = 1
    movq $1, %r9
    movq %r9, T_18

#   T_19 = i + T_18
    movq i, %r8
    movq T_18, %r9
    addq %r9, %r8
    movq %r8, T_19

#   i = T_19
    movq T_19, %r9
    movq %r9, i

    jmp L_13

L_12:

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
test3:	.quad 0
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
T_16:	.quad 0
T_17:	.quad 0
T_18:	.quad 0
T_19:	.quad 0
