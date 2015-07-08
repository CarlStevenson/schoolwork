# count.s

.section .text

.globl _start

_start:

    movq	$prompt, %rdi 
	movq	$0, %rax 
	call	printf 

	movq	$ins, %rdi
	movq	$num, %rsi 
	movq	$0, %rax 
	call	scanf
	
	movq	$0, %r13
	movq	num, %r12
loop:
	cmpq	%r13, %r12 
	je		done  

	movq	$outs, %rdi
	movq	%r13, %rsi
	movq    num, %rdx
	subq    %rsi, %rdx
	subq    $1, %rdx
	movq	$0, %rax
	call	printf 

	incq	%r13 # Increment the loop control
	jmp		loop

done:
	movq	$0, %rdi
	call	exit
	 



.section .rodata

prompt:     .string "What would you like to count to? : "
ins:        .string "%ld"
outs:       .string "%ld %ld\n"

.section .data

num:        .quad 0
