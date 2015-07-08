.section .text

.globl main
main: enter $0, $0

#   read
    movq $readfmt, %rdi
    movq $i, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $j, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $k, %rsi
    movq $0, %rax
    call scanf
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
    movq i, %rsi
    movq i, %rdx
    movq $0, %rax
    call printf

#   write j
    movq $writefmt, %rdi
    movq j, %rsi
    movq j, %rdx
    movq $0, %rax
    call printf

#   write k
    movq $writefmt, %rdi
    movq k, %rsi
    movq k, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   write f
    movq $writefmt, %rdi
    movq i, %rsi
    movq f, %rdx
    movq $0, %rax
    call printf

#   write g
    movq $writefmt, %rdi
    movq j, %rsi
    movq g, %rdx
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
