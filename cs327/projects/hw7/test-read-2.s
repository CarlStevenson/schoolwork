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
#   T_0 = 1
    movq $1, %r9
    movq %r9, T_0

#   T_1 = i + T_0
    movq i, %r8
    movq T_0, %r9
    addq %r9, %r8
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

#   T_2 = 2
    movq $2, %r9
    movq %r9, T_2

#   T_3 = j - T_2
    movq j, %r8
    movq T_2, %r9
    subq %r9, %r8
    movq %r8, T_3

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
T_2:	.quad 0
T_3:	.quad 0
