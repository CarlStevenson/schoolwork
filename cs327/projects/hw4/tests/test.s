.section .text

.globl main
main: enter $0, $0

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


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

T_0:	.quad 0
