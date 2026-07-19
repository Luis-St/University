.section .data
val1:	.quad 1
val2:	.quad 2
val3:	.quad 3
val4:	.quad 4
val5:	.quad 5
factor:	.quad 3

.section .text
.global _start
_start:
	movq val1(%rip), %rax
	addq val2(%rip), %rax
	addq val3(%rip), %rax
	addq val4(%rip), %rax
	addq val5(%rip), %rax
	imulq factor(%rip), %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall
