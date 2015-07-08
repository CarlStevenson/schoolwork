.section .text

.globl main
main: enter $0, $0

#   T_0 = 12
    movq $12, %r9
    movq %r9, T_0

#   n = T_0
    movq T_0, %r9
    movq %r9, n

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   T_2 = 3
    movq $3, %r9
    movq %r9, T_2

#   T_3 = n + T_2
    movq n, %r8
    movq T_2, %r9
    addq %r9, %r8
    movq %r8, T_3

#   i = T_1
    movq T_1, %r9
    movq %r9, i

L_0:

#   if T_3 less than i goto L_1
    movq i, %r8
    movq T_3, %r9
    cmpq %r8, %r9
    jl L_1

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   write n
    movq $writefmt, %rdi
    movq n, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_4 = 1
    movq $1, %r9
    movq %r9, T_4

#   T_5 = n + T_4
    movq n, %r8
    movq T_4, %r9
    addq %r9, %r8
    movq %r8, T_5

#   n = T_5
    movq T_5, %r9
    movq %r9, n

#   i = i + 1
    movq i, %r8
    addq $1, %r8
    movq %r8, i
#   goto L_0
    jmp L_0

L_1:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

i:	.quad 0
n:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
