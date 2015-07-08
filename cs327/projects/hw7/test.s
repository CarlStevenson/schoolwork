.section .text

.globl main
main: enter $0, $0

#   a = 1
    movq $1, %r9
    movq %r9, a

    jmp L_2

#   a = 0
    movq $0, %r9
    movq %r9, a

L_2:

    jmp L_4

#   b = 1
    movq $1, %r9
    movq %r9, b

    jmp L_5

L_4:

#   b = 0
    movq $0, %r9
    movq %r9, b

L_5:

#   T_0 = 6
    movq $6, %r9
    movq %r9, T_0

#   c = T_0
    movq T_0, %r9
    movq %r9, c

#   write c
    movq $writefmt, %rdi
    movq $8, %rsi
    movq c, %rdx
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

    movq $writetrue, %rdi
    movq $0, %rax
    call printf

    movq $writefalse, %rdi
    movq $0, %rax
    call printf

#   writeln
    movq $writelnfmt, %rdi
    movq $0, %rax
    call printf

#   read
    movq $readfmt, %rdi
    movq $c, %rsi
    movq $0, %rax
    call scanf
#   read
    movq $readfmt, %rdi
    movq $d, %rsi
    movq $0, %rax
    call scanf
#   write c
    movq $writefmt, %rdi
    movq $8, %rsi
    movq c, %rdx
    movq $0, %rax
    call printf

#   write d
    movq $writefmt, %rdi
    movq $8, %rsi
    movq d, %rdx
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

a:	.quad 0
b:	.quad 0
c:	.quad 0
d:	.quad 0
test:	.quad 0
T_0:	.quad 0
