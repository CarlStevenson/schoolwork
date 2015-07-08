.section .text

.globl main
main: enter $0, $0

#   read
    movq $readfmt, %rdi
    movq $i, %rsi
    movq $0, %rax
    call scanf
#   write i
    movq $writefmt, %rdi
    movq $8, %rsi
    movq i, %rdx
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
test:	.quad 0
