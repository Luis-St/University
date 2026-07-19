.global _start
.section .text
_start:
	movq $5, %rdi
	call factorial_loop
	movq %rax, %rdi
	movq $60, %rax
	syscall

factorial_loop:
	push %rcx
	push %rdi
	movq $1, %rax
	movq $1, %rcx
factorial_loop_top:
	cmpq %rdi, %rcx
	jg factorial_loop_end
	imulq %rcx, %rax
	incq %rcx
	jmp factorial_loop_top
factorial_loop_end:
	pop %rdi
	pop %rcx
	ret
