# ftokfunc.s
# ftokfunc converts a Fahrenheit value to Kelvin

.section .text

.globl main

main:

    enter   $16, $0
    
	movq	$prompt, %rdi
	movq	$0, %rax
	call	printf

	movq	$ins, %rdi
	leaq	-8(%rbp), %rsi
	movq	$1, %rax
	call	scanf
	
	movq    -8(%rbp), %rdi
	call    conv

    movq    $outs, %rdi
	movq    $2, %rax
	call    printf
	
	movq    $0, %rax
	leave
	ret

conv:
    
    enter   $0, $0
    
	cvtsi2sd   %rdi, %xmm0
	movsd   %xmm0, %xmm1
	movq    $32, %r8   
	cvtsi2sd    %r8, %xmm3
    subsd   %xmm3, %xmm1
    movq    $5, %r8
    cvtsi2sd    %r8, %xmm3
	mulsd   %xmm3, %xmm1
	movq    $9, %r8
    cvtsi2sd    %r8, %xmm3
	divsd   %xmm3, %xmm1
	movq    $273, %r8
    cvtsi2sd    %r8, %xmm3
	addsd   %xmm3, %xmm1
	
    leave
    
    ret
.section .rodata

prompt:		.string "Enter a Fahrenheit temperature: "
ins:		.string "%ld"
outs:		.string "%0.4f F = %0.4f K \n"



