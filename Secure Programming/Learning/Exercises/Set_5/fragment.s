.globl _start
.section .text
_start:
    movq $3,   %rax
    imul v1,   %rax
    divq v2
    movq %rax, %rdi
    movq $60,  %rax
    syscall
.section .data
v1: .quad 4
v2: .quad 2
