.global _start
.section .text
_start:
	movq $4, %rax
	addq $4, %rax
	imulq $4, %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
