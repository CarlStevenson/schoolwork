.section .text

.globl main
main: enter $0, $0

#   T_0 = 0
    movq $0, %r9
    movq %r9, T_0

#   a = T_0
    movq T_0, %r9
    movq %r9, a

L_1:

    movq a, %r8
    movq $1, %r9
    addq %r8, %r9
    movq %r9, a
    movq %r9, T_1
#   T_2 = 10
    movq $10, %r9
    movq %r9, T_2

#   if T_1 <= T_2 goto L_2
    movq T_1, %r8
    movq T_2, %r9
    cmpq %r9, %r8
    jle L_2

    jmp L_0

L_2:

#   b = a
    movq a, %r9
    movq %r9, b

L_4:

    movq b, %r8
    movq $1, %r9
    subq %r9, %r8
    movq %r9, b
    movq %r8, T_3
#   T_4 = 0
    movq $0, %r9
    movq %r9, T_4

#   if T_3 > T_4 goto L_5
    movq T_3, %r8
    movq T_4, %r9
    cmpq %r9, %r8
    jg L_5

    jmp L_3

L_5:

#   write b
    movq $writefmt, %rdi
    movq b, %rsi
    movq $0, %rax
    call printf

    jmp L_4

L_3:

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    jmp L_1

L_0:


  movq $0, %rax

  leave
  ret

.section .rodata

writefmt: .string "%8ld"
writelnfmt: .string "\n"

.section .data

a:	.quad 0
b:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
testIncr5:	.quad 0
