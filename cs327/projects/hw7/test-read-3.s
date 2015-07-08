.section .text

.globl main
main: enter $0, $0

#   read
    movq $readfmt, %rdi
    movq $i1, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i2, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i3, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i4, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i5, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i6, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i7, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i8, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $i9, %rsi
    movq $0, %rax
    call scanf
#   T_0 = i1 + i2
    movq i1, %r8
    movq i2, %r9
    addq %r9, %r8
    movq %r8, T_0

#   write T_0
    movq $writefmt, %rdi
    movq $8, %rsi
    movq T_0, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_1 = i3 * i4
    movq i3, %r8
    movq i4, %r9
    imulq %r9, %r8
    movq %r8, T_1

#   write T_1
    movq $writefmt, %rdi
    movq $8, %rsi
    movq T_1, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_2 = i5 / i6
    movq i5, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq i6, %r9
    idivq %r9
    movq %rax, T_2

#   write T_2
    movq $writefmt, %rdi
    movq $8, %rsi
    movq T_2, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_3 = i7 / i8
    movq i7, %rax
    movq %rax, %rdx
    sarq $63, %rdx
    movq i8, %r9
    idivq %r9
    movq %rdx, T_3

#   write T_3
    movq $writefmt, %rdi
    movq $8, %rsi
    movq T_3, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   T_4 = i1 + i2
    movq i1, %r8
    movq i2, %r9
    addq %r9, %r8
    movq %r8, T_4

#   T_5 = T_4 + i3
    movq T_4, %r8
    movq i3, %r9
    addq %r9, %r8
    movq %r8, T_5

#   T_6 = T_5 + i4
    movq T_5, %r8
    movq i4, %r9
    addq %r9, %r8
    movq %r8, T_6

#   T_7 = T_6 + i5
    movq T_6, %r8
    movq i5, %r9
    addq %r9, %r8
    movq %r8, T_7

#   T_8 = T_7 + i6
    movq T_7, %r8
    movq i6, %r9
    addq %r9, %r8
    movq %r8, T_8

#   T_9 = T_8 + i7
    movq T_8, %r8
    movq i7, %r9
    addq %r9, %r8
    movq %r8, T_9

#   T_10 = T_9 + i8
    movq T_9, %r8
    movq i8, %r9
    addq %r9, %r8
    movq %r8, T_10

#   T_11 = T_10 + i9
    movq T_10, %r8
    movq i9, %r9
    addq %r9, %r8
    movq %r8, T_11

#   write T_11
    movq $writefmt, %rdi
    movq $8, %rsi
    movq T_11, %rdx
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

test:	.quad 0
i1:	.quad 0
i2:	.quad 0
i3:	.quad 0
i4:	.quad 0
i5:	.quad 0
i6:	.quad 0
i7:	.quad 0
i8:	.quad 0
i9:	.quad 0
T_0:	.quad 0
T_1:	.quad 0
T_2:	.quad 0
T_3:	.quad 0
T_4:	.quad 0
T_5:	.quad 0
T_6:	.quad 0
T_7:	.quad 0
T_8:	.quad 0
T_9:	.quad 0
T_10:	.quad 0
T_11:	.quad 0
