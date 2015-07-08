# array.s - Reads at most 100 floating point numbers into an array
#           and computes their sum.

.section .text

.globl	main
main:
	enter 	$816, $0		# Set up the stack frame
	
	movq	$prompt1, %rdi	# Prompt for the prompt
	movq	$0, %rax
	call	printf
	
	movq	$intfmt, %rdi	# Read the count
	leaq	-816(%rbp), %rsi
	movq	$0, %rax
	call	scanf
	
	movq	$prompt2, %rdi	# Prompt for the numbers
	movq	$0, %rax
	call	printf
	
	movq	-816(%rbp), %rdi		# Read the numbers into the array
	leaq	-808(%rbp), %rsi
	call	readarray

	movq	-816(%rbp), %rdi		# Add the numbers
	leaq	-808(%rbp), %rsi
	call	sumarray
	
	movq	$outfmt, %rdi	# Print the sum
	movq	$1, %rax
	call	printf
	
	movq	$0, %rax	# Return
	leave
	ret

# readarray(n, a) - Reads n doubles into the array a

readarray:
	enter 	$0, $0		# Set up the stack frame

	pushq	%r12		# Save registers
	pushq	%r13
	pushq	%r14

	movq	%rdi, %r12	# Initialize the loop bound
	movq	$0, %r13	# Initialize the loop control
	movq	%rsi, %r14	# Store the address of the array
loop1:
	cmpq	%r13, %r12	# Test the loop control
	je	done1
	movq	$fltfmt, %rdi

	lea	(%r14,%r13,8), %rsi # Read one element of the array
	movq	$0, %rax
	call	scanf

	incq	%r13		# Increment the loop control
	jmp 	loop1		# Loop
done1:
	popq 	%r14		# Restore registers
	popq 	%r13
	popq 	%r12

	leave			# Remove the stack frame and return
	ret	

# sumarray(n, a) - Returns the sum of the first n doubles
#                  in the array a

sumarray:
	enter 	$0, $0		# Set up the stack frame

	pushq	%r12		# Save registers
	pushq	%r13
	pushq	%r14

	movq	%rdi, %r12	# Initialize the loop bound
	movq	$0, %r13	# Initialize the loop control
	subsd	%xmm0, %xmm0	# Initialize the sum
	movq	%rsi, %r14	# Store the address of the array
loop2:
	cmpq	%r13, %r12	# Test the loop control
	je	done2

	addsd	(%r14,%r13,8), %xmm0 # Add an item to the sum

	incq	%r13		# Increment the loop control
	jmp 	loop2		# Loop
done2:
	popq 	%r14		# Restore registers
	popq 	%r13
	popq 	%r12
	
	leave			# Remove the stack frame and return
	ret	

.section .rodata
	
prompt1:	.string "How many numbers (must be at most 100)? "
prompt2:	.string "Enter the numbers: "
intfmt:		.string "%ld"
fltfmt:		.string "%lf"
outfmt:		.string "The sum is %f\n"

