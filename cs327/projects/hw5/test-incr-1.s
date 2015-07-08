.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   a = T_0
    movq T_0, %r9
    movq %r9, a

#   write a
    movq $writefmt, %rdi
    movq a, %rsi
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
    movq %r9, T_1
#   write a
    movq $writefmt, %rdi
    movq a, %rsi
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
#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq a, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, a
    movq %r8, T_3
#   write a
    movq $writefmt, %rdi
    movq a, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq a, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, a
    movq %r8, T_4
#   write a
    movq $writefmt, %rdi
    movq a, %rsi
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
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
testIncr1:	.quad 0
