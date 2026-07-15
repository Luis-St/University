.globl _start
.section .text
_start:
_loop:
    movq $0,       %rdi
    movq $buffer,  %rsi
    movq $1,       %rdx
    movq $0,       %rax
    syscall
    cmpq $0,       %rax
    je   _end
    movq $1,       %rdi
    movq $buffer,  %rsi
    movq $1,       %rdx
    movq $1,       %rax
    syscall
    jmp  _loop
_end:
    movq $0,       %rdi
    movq $60,      %rax
    syscall
.section .data
buffer: .ascii "\0"
