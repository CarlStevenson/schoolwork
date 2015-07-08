# square.s
# modified from code provided by Dr. Sullivan
# written by Carl Stevenson

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
	movq    num, %r14
	subq    $1, %r14
loop:
	cmpq	%r13, %r12 
	je		second  

	movq	$outs, %rdi
	movq	%r13, %rsi
	movq	$0, %rax
	call	printf 

	incq	%r13 # Increment the loop control
	jmp		loop
	
second:
    cmpq    %r15, %r14
    je      done
    
    movq    $0, %r13
    
    movq    $newline, %rdi
    movq    $0, %rax
    call    printf
    
    incq    %r15
    jmp     loop

done:
    movq    $newline, %rdi
    movq    $0, %rax
    call    printf
    
	movq	$0, %rdi
	call	exit
	 



.section .rodata

prompt:     .string "What would you like to count to? : "
ins:        .string "%ld"
newline:    .string "\n"
outs:       .string "%ld "

.section .data

num:        .quad 0
