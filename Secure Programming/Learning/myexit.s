# s myexit.s -o myexit.o && ld myexit.o -o myexit && ./myexit
# echo $?

.global _start
.section .text
_start:
	# movq = move quadword (64-bit/8-byte value)
	movq $60, %rax # Syscall exit
	movq $4, %rdi # Load value 3 into register rdi
	syscall
