.global _start
.section .text
_start:
	movq $5, %rdi
	call sum_loop
	imulq $3, %rax
	movq %rax, %rdi
	movq $60, %rax
	syscall

sum_loop:
	push %rcx
	push %rdi
	movq $0, %rax
	movq $1, %rcx
sum_loop_top:
	cmpq %rdi, %rcx
	jg sum_loop_end
	addq %rcx, %rax
	incq %rcx
	jmp sum_loop_top
sum_loop_end:
	pop %rdi
	pop %rcx
	ret
