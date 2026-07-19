.global _start
.section .text
_start:
	movq $4, %rax
	addq $2, %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
