.section .data
filename:
	.asciz "input.bin"
val1:
	.quad 4
val2:
	.quad 6

.section .text
.global _start
_start:
	movq $2, %rax
	movq $filename, %rdi
	movq $0x241, %rsi
	movq $0x1A4, %rdx
	syscall
	movq %rax, %rbx

	movq $1, %rax
	movq %rbx, %rdi
	movq $val1, %rsi
	movq $16, %rdx
	syscall

	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq $60, %rax
	movq $0, %rdi
	syscall
