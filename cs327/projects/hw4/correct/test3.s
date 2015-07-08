.section .text

.globl main
main: enter $0, $0

#   T_0 = 10
    movq $10, %r9
    movq %r9, T_0

#   n = T_0
    movq T_0, %r9
    movq %r9, n

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   abc = T_1
    movq T_1, %r9
    movq %r9, abc

L_0:

#   if n less than abc goto L_1
    movq abc, %r8
    movq n, %r9
    cmpq %r8, %r9
    jl L_1

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

#   T_3 = n - T_2
    movq n, %r8
    movq T_2, %r9
    subq %r9, %r8
    movq %r8, T_3

#   n = T_3
    movq T_3, %r9
    movq %r9, n

#   write n
    movq $writefmt, %rdi
    movq n, %rsi
    movq $0, %rax
    call printf

#   write abc
    movq $writefmt, %rdi
    movq abc, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   abc = abc + 1
    movq abc, %r8
    addq $1, %r8
    movq %r8, abc
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

n:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
abc:	.quad 0
