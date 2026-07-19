.section .data
val1:	.quad 4
val2:	.quad 2

.section .text
.global _start
_start:
	movq val1(%rip), %rax
	addq val2(%rip), %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
