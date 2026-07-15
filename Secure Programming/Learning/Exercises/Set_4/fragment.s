.globl _start
.section .text
_start:
    movq $shell, %rdi
    movq $args,  %rsi
    movq $env,   %rdx
    movq $59,    %rax
    syscall
    movq $99,    %rdi
    movq $60,    %rax
    syscall
.section .data
shell:  .ascii "/bin/sh\0"
args:   .quad shell
        .quad 0
env:    .quad 0
