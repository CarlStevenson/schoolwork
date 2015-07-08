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
#   write T_1
    movq $writefmt, %rdi
    movq T_1, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_2 = 1
    movq $1, %r9
    movq %r9, T_2

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

#   a = T_3
    movq T_3, %r9
    movq %r9, a

    movq a, %r8
    movq $-1, %r9
    imul %r9, %r8
    movq %r8, T_4
#   write T_4
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_5 = 1
    movq $1, %r9
    movq %r9, T_5

#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
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

testUnary1:	.quad 0
a:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
