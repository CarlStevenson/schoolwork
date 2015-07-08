# minmax.s

.section .text

.globl _start

_start:

        movq    $prompt, %rdi
        movq    $0, %rax
        call    printf
        
        movq    $ins, %rdi
        movq    $left, %rsi
        movq    $right, %rdx
        movq    $0, %rax
        call    scanf
        
        movq    left, %r8
        movq    right, %r9
        cmpq    %r8, %r9
        jl      less
        jmp     more
        
less:
        movq    $outs, %rdi
        movq    left, %rsi
        movq    right, %rdx
        movq    $0, %rax
        call    printf
        
        jmp     end
more:

        movq    $outs, %rdi
        movq    left, %rdx
        movq    right, %rsi
        movq    $0, %rax
        call    printf
        
        jmp     end
end:

        movq    $0, %rdi
        call    exit
        
.section .rodata

prompt:     .string "Enter two numbers: "
ins:        .string "%ld %ld"
outs:       .string "\nMax: %ld Min: %ld\n"
.section .data

left:       .quad 0
right:      .quad 0
