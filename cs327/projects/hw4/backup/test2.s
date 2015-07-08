T_0 = 0
ifnot T_0 goto L_0
T_1 = 1
write T_1
L_0:
T_2 = 3
T_3 = 4
T_4 = T_2 - T_3
ifnot T_4 goto L_1
T_5 = 1
write T_5
goto L_2
L_1:
T_6 = 2
write T_6
L_2:
writeln
.section .text

.globl main
main: enter $0, $0

#   T_0 = 0
    movq $0, %r9
    movq %r9, T_0

#   ifnot T_0 goto L_0
    movq T_0, %r8
    testq %r8, %r8
    jz L_0

#   T_1 = 1
    movq $1, %r9
    movq %r9, T_1

#   write T_1
    movq $writefmt, %rdi
    movq T_1, %rsi
    movq $0, %rax
    call printf

L_0:

#   T_2 = 3
    movq $3, %r9
    movq %r9, T_2

#   T_3 = 4
    movq $4, %r9
    movq %r9, T_3

#   T_4 = T_2 - T_3
    movq T_2, %r8
    movq T_3, %r9
    subq %r9, %r8
    movq %r8, T_4

#   ifnot T_4 goto L_1
    movq T_4, %r8
    testq %r8, %r8
    jz L_1

#   T_5 = 1
    movq $1, %r9
    movq %r9, T_5

#   write T_5
    movq $writefmt, %rdi
    movq T_5, %rsi
    movq $0, %rax
    call printf

#   goto L_2
    jmp L_2

L_1:

#   T_6 = 2
    movq $2, %r9
    movq %r9, T_6

#   write T_6
    movq $writefmt, %rdi
    movq T_6, %rsi
    movq $0, %rax
    call printf

L_2:

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
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
