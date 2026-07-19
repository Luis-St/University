.section .data
filename:
	.asciz "hello.txt"
msg:
	.ascii "Hello World"
msglen = . - msg

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
	movq $msg, %rsi
	movq $msglen, %rdx
	syscall

	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq $60, %rax
	movq $0, %rdi
	syscall
