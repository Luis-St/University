.globl _start
.section .text
_start:
    movq $1,       %rdi
    movq $hello,   %rsi
    movq numchar,  %rdx
    movq $1,       %rax
    syscall
    movq $0,       %rdi
    movq $60,      %rax
    syscall
.section .data
hello:   .ascii "Hello World!\n"
numchar: .quad  13
