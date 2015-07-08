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

#   write j
    movq $writefmt, %rdi
    movq i, %rsi
    movq j, %rdx
    movq $0, %rax
    call printf

#   write k
    movq $writefmt, %rdi
    movq j, %rsi
    movq k, %rdx
    movq $0, %rax
    call printf

#   write f
    movq $writefmt, %rdi
    movq $8, %rsi
    movq f, %rdx
    movq $0, %rax
    call printf

#   write g
    movq $writefmt, %rdi
    movq i, %rsi
    movq g, %rdx
    movq $0, %rax
    call printf

#   T_3 = 7
    movq $7, %r9
    movq %r9, T_3

#   T_4 = 2
    movq $2, %r9
    movq %r9, T_4

#   T_5 = k + T_4
    movq k, %r8
    movq T_4, %r9
    addq %r9, %r8
    movq %r8, T_5

#   write T_3
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq T_3, %rdx
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
