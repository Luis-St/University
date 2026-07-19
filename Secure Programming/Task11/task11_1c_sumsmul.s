.global _start
.section .text
_start:
	movq $1, %rax
	addq $2, %rax
	addq $3, %rax
	addq $4, %rax
	addq $5, %rax
	imulq $3, %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
