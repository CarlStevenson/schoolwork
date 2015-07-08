# loopm.s

.section .text

.globl main

main:

    enter   $8, $0
    
    movq    $prompt, %rdi
    movq    $0, %rax
    call    printf
    
    movq    $ins, %rdi
    leaq    -8(%rbp), %rsi
    movq    $0, %rax
    call    scanf
    
    movq	$0, %r13
	movq	-8(%rbp), %r12
	
loop:
	cmpq	%r13, %r12 
	je		done  

	movq	$outs, %rdi
	movq	%r13, %rsi
	movq    %r12, %rdx
	subq    %rsi, %rdx
	subq    $1, %rdx
	movq	$0, %rax
	call	printf 

	incq	%r13 # Increment the loop control
	jmp		loop

done:
    
	movq	$0, %rax
	leave
	ret
	 


.section .rodata

prompt:     .string "What would you like to count to? : "
ins:        .string "%ld"
outs:       .string "%ld %ld\n"

