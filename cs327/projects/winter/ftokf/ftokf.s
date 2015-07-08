# ftokf.s
# ftokf converts a Fahrenheit value to Kelvin

.section .text

.globl _start

_start:
	movq	$prompt, %rdi
	movq	$0, %rax
	call	printf

	movq	$ins, %rdi
	movq	$val, %rsi
	movq	$0, %rax
	call	scanf

	movsd   val, %xmm0
	movsd   %xmm0, %xmm1   
	cvtsi2sd    convert32, %xmm3
    subsd   %xmm3, %xmm1
    cvtsi2sd    convert5, %xmm3
	mulsd   %xmm3, %xmm1
    cvtsi2sd    convert9, %xmm3
	divsd   %xmm3, %xmm1
    cvtsi2sd    convert273, %xmm3
	addsd   %xmm3, %xmm1
	
	movq    $outs, %rdi
	movq    $2, %rax
	call    printf
	# exit the program
	movq	$0, %rdi
	call	exit


.section .rodata

prompt:		.string "Enter a Fahrenheit temperature: "
ins:		.string "%lf"
outs:		.string "%0.4f F = %0.4f K \n"

.section .data

val:		.quad 0
convert32:  .quad 32
convert5:   .quad 5
convert9:   .quad 9
convert273: .quad 273
