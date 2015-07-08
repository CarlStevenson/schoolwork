# ftok.s
# ftok converts a Fahrenheit value to Kelvin

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

	# load the temp in F to rdx
	movq	val, %rdx
	# load a memory location r11 with the value 10 for rounding
	movq	$10, %r11
	# multiply the temp in rdx by 10
	imul	%r11, %rdx
	# subtract 320 from the temp in rdx
	subq	$320, %rdx
	# load up and multiply 5
	movq	$5, %r11
	imul	%r11, %rdx
	# divide by 9, quotient in rax
	movq	$9, %r11
	movq	%rdx, %rax
	movq	%rax, %rdx
	sarq	$63, %rdx
	idivq	%r11
	# add 5 for rounding to the quotient in rax
	addq	$5, %rax
	# divide by 10, quotient in rax
	movq	$10, %r11
	movq	%rax, %rdx
	sarq	$63, %rdx
	idivq	%r11
	# add 273 for C to K
	addq	$273, %rax
	# move the quotient to rdx for output
	movq	%rax, %rdx
	movq	$outs, %rdi
	movq	val, %rsi
	movq	$0, %rax
	call	printf

	# exit the program
	movq	$0, %rdi
	call	exit


.section .rodata

prompt:		.string "Enter a Fahrenheit temperature: "
ins:		.string "%ld"
outs:		.string "%ld F = %ld K \n"

.section .data

val:		.quad 0
