.section .text

.globl main
main: enter $0, $0

#   T_0 = 0
    movq $0, %r9
    movq %r9, T_0

#   x = T_0
    movq T_0, %r9
    movq %r9, x

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   y = T_1
    movq T_1, %r9
    movq %r9, y

#   s = 1
    movq $1, %r9
    movq %r9, s

    jmp L_2

#   s = 0
    movq $0, %r9
    movq %r9, s

L_2:

    jmp L_4

#   t = 1
    movq $1, %r9
    movq %r9, t

    jmp L_5

L_4:

#   t = 0
    movq $0, %r9
    movq %r9, t

L_5:

#   if s goto L_9
    movq s, %r8
    testq %r8, %r8
    jnz L_9

    jmp L_8

L_9:

#   if t goto L_7
    movq t, %r8
    testq %r8, %r8
    jnz L_7

    jmp L_8

L_7:

#   write x
    movq $writefmt, %rdi
    movq x, %rsi
    movq $0, %rax
    call printf

    jmp L_6

L_8:

#   write y
    movq $writefmt, %rdi
    movq y, %rsi
    movq $0, %rax
    call printf

L_6:

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

tc13:	.quad 0
s:	.quad 0
t:	.quad 0
x:	.quad 0
y:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
