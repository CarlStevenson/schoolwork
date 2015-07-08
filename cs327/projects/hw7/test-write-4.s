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

#   f = 1
    movq $1, %r9
    movq %r9, f

    jmp L_2

#   f = 0
    movq $0, %r9
    movq %r9, f

L_2:

    jmp L_4

#   g = 1
    movq $1, %r9
    movq %r9, g

    jmp L_5

L_4:

#   g = 0
    movq $0, %r9
    movq %r9, g

L_5:

#   write i
    movq $writefmt, %rdi
    movq $8, %rsi
    movq i, %rdx
    movq $0, %rax
    call printf

#   T_3 = 5
    movq $5, %r9
    movq %r9, T_3

#   write j
    movq $writefmt, %rdi
    movq T_3, %rsi
    movq j, %rdx
    movq $0, %rax
    call printf

#   T_4 = 6
    movq $6, %r9
    movq %r9, T_4

#   write k
    movq $writefmt, %rdi
    movq T_4, %rsi
    movq k, %rdx
    movq $0, %rax
    call printf

#   write f
    movq $writefmt, %rdi
    movq $8, %rsi
    movq f, %rdx
    movq $0, %rax
    call printf

#   T_5 = 12
    movq $12, %r9
    movq %r9, T_5

#   write g
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq g, %rdx
    movq $0, %rax
    call printf

#   T_6 = 7
    movq $7, %r9
    movq %r9, T_6

#   T_7 = 8
    movq $8, %r9
    movq %r9, T_7

#   write T_6
    movq $writefmt, %rdi
    movq T_7, %rsi
    movq T_6, %rdx
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

f:	.quad 0
g:	.quad 0
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
T_6:	.quad 0
T_7:	.quad 0
