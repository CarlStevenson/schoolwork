# loop.s - Reads an integer n and prints the integers
# from 0 to n - 1.
# modified from code provided by Dr. Sullivan
# written by Carl Stevenson

.section .text

.globl _start

_start:
	movq	$prompt, %rdi 
	movq	$0, %rax 
	call	printf 

	movq	$infmt, %rdi # Read the upper bound
	movq	$n, %rsi 
	movq	$0, %rax 
	call	scanf
	
	movq	$0, %r13 # Initialize the loop control
	movq	n, %r12  # store the upper bound in a preserved register
loop:
	cmpq	%r13, %r12 
	je		done  

	movq	$outfmt, %rdi
	movq	%r13, %rsi
	movq	$0, %rax
	call	printf 

	incq	%r13 # Increment the loop control
	jmp		loop

done:
	movq	$0, %rdi
	call	exit
	 
.section .rodata

prompt:		.string "Enter a size: "
infmt:		.string "%ld"
outfmt:		.string "%3ld\n"

.section .data

n:			.quad 0
