.section .data
filename:
	.asciz "input.bin"

.section .bss
buf:
	.skip 16

.section .text
.global _start
_start:
	movq $2, %rax
	movq $filename, %rdi
	movq $0, %rsi
	syscall
	movq %rax, %rbx

	movq $0, %rax
	movq %rbx, %rdi
	movq $buf, %rsi
	movq $16, %rdx
	syscall

	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq buf(%rip), %rax
	addq buf+8(%rip), %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
