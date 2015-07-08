.section .text

.globl main
main: enter $0, $0

L_1:

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   write T_0
    movq $writefmt, %rdi
    movq T_0, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_3

#   T_1 = 1
    movq $1, %r9
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

    jmp L_6

L_7:

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   write T_2
    movq $writefmt, %rdi
    movq T_2, %rsi
    movq $0, %rax
    call printf

L_6:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_9

    jmp L_9

#   T_3 = 1
    movq $1, %r9
    movq %r9, T_3

#   write T_3
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq $0, %rax
    call printf

L_9:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_13

L_13:

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   write T_4
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_16

    jmp L_15

L_16:

#   T_5 = 1
    movq $1, %r9
    movq %r9, T_5

#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq $0, %rax
    call printf

L_15:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

L_19:

#   T_6 = 1
    movq $1, %r9
    movq %r9, T_6

#   write T_6
    movq $writefmt, %rdi
    movq T_6, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_21

#   T_7 = 1
    movq $1, %r9
    movq %r9, T_7

#   write T_7
    movq $writefmt, %rdi
    movq T_7, %rsi
    movq $0, %rax
    call printf

L_21:

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

test1:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
T_7:	.quad 0
