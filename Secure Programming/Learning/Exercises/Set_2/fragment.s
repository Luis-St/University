.globl _start
.section .text
_start:
    movq $4,    %r11
    movq $3,    %rax
    movq $2,    %r12
    imul %r11,  %rax
    divq %r12
    sal  $2,    %rax
    movq %rax,  %rdi
    movq $60,   %rax
    syscall
