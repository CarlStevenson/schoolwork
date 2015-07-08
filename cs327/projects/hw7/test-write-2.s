.section .text

.globl main
main: enter $0, $0

#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   i = T_0
    movq T_0, %r9
    movq %r9, i

#   T_1 = 2
    movq $2, %r9
    movq %r9, T_1

#   j = T_1
    movq T_1, %r9
    movq %r9, j

#   write i
    movq $writefmt, %rdi
    movq $8, %rsi
    movq i, %rdx
    movq $0, %rax
    call printf

#   write j
    movq $writefmt, %rdi
    movq $8, %rsi
    movq j, %rdx
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

writefmt: .string "%*ld"
writelnfmt: .string "\n"
writetrue: .string "true"
writefalse: .string "false"
readfmt: .string "%d"

.section .data

i:	.quad 0
j:	.quad 0
test:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
