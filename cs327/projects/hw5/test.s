.section .text

.globl main
main: enter $0, $0

#   T_0 = 10
    movq $10, %r9
    movq %r9, T_0

#   a = T_0
    movq T_0, %r9
    movq %r9, a

L_1:

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   T_2 = a - T_1
    movq a, %r8
    movq T_1, %r9
    subq %r9, %r8
    movq %r8, T_2

#   a = T_2
    movq T_2, %r9
    movq %r9, a

    jmp L_1


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

a:	.quad 0
test:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
