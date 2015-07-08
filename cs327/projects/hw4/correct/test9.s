.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = 0
    movq $0, %r9
    movq %r9, T_1

#   T_2 = 12
    movq $12, %r9
    movq %r9, T_2

#   T_3 = T_1 - T_2
    movq T_1, %r8
    movq T_2, %r9
    subq %r9, %r8
    movq %r8, T_3

#   i = T_0
    movq T_0, %r9
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

#   i = i + 1
    movq i, %r8
    addq $1, %r8
    movq %r8, i
#   goto L_0
    jmp L_0

L_1:

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
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
