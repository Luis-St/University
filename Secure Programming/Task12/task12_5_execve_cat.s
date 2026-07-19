.section .data
path1:
	.asciz "/etc/passwd"
path2:
	.asciz "/etc/shadow"
path3:
	.asciz "/home/luis/.ssh/known_hosts"
cat_path:
	.asciz "/usr/bin/cat"

.section .bss
.align 8
argv:
	.skip 40
envp:
	.skip 8

.section .text
.global _start
_start:
	movq $2, %rax
	movq $path1, %rdi
	movq $0, %rsi
	syscall
	cmpq $0, %rax
	js fail
	movq %rax, %rbx
	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq $2, %rax
	movq $path2, %rdi
	movq $0, %rsi
	syscall
	cmpq $0, %rax
	js fail
	movq %rax, %rbx
	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq $2, %rax
	movq $path3, %rdi
	movq $0, %rsi
	syscall
	cmpq $0, %rax
	js fail
	movq %rax, %rbx
	movq $3, %rax
	movq %rbx, %rdi
	syscall

	movq $cat_path, argv(%rip)
	movq $path1, argv+8(%rip)
	movq $path2, argv+16(%rip)
	movq $path3, argv+24(%rip)
	movq $0, argv+32(%rip)
	movq $0, envp(%rip)

	movq $59, %rax
	movq $cat_path, %rdi
	movq $argv, %rsi
	movq $envp, %rdx
	syscall

fail:
	movq $60, %rax
	movq $1, %rdi
	syscall
