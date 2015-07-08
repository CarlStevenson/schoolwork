.section .text

.globl main
main: 



#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = 10
    movq $10, %r9
    movq %r9, T_1

#   i = T_0
    movq T_0, %r9
    movq %r9, i

L_0:

#   if T_1 less than i goto L_1
    movq i, %r8
    movq T_1, %r9
    cmpq %r8, %r9
    jl L_1

#   write i
    movq $writefmt, %rdi
    movq i, %rsi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   i = i + 1
    movq i, %r8
    addq $1, %r8
    movq %r8, i
#   goto L_0
    jmp L_0

L_1:


  movq $0 , %rax

  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

i:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
