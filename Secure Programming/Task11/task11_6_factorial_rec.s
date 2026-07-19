.global _start
.section .text
_start:
	movq $5, %rdi
	call factorial_rec
	movq %rax, %rdi
	movq $60, %rax
	syscall

factorial_rec:
	cmpq $1, %rdi
	jg factorial_rec_recurse
	movq $1, %rax
	ret
factorial_rec_recurse:
	push %rdi
	decq %rdi
	call factorial_rec
	pop %rdi
	imulq %rdi, %rax
	ret
