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

#   T_2 = 3
    movq $3, %r9
    movq %r9, T_2

#   k = T_2
    movq T_2, %r9
    movq %r9, k

#   T_3 = 4
    movq $4, %r9
    movq %r9, T_3

#   write i
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq i, %rdx
    movq $0, %rax
    call printf

#   T_4 = 5
    movq $5, %r9
    movq %r9, T_4

#   write j
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq j, %rdx
    movq $0, %rax
    call printf

#   T_5 = 6
    movq $6, %r9
    movq %r9, T_5

#   write k
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq k, %rdx
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
k:	.quad 0
test:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
