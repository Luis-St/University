.globl _start
.section .text
_start:
_loop:

	# ssize_t read(int fd, void buf[.count], size_t count);
	# ssize_t write(int fd, const void buf[.count], size_t count);
	# Syscall %rax
	# Parm0 %rdi
	# Parm1 %rsi
	# Parm2 %rdx
	# Parm3 %r10
	# Parm4 %r8
	# Parm5 %r9

	movq $0,	   %rdi	# Load 0 into rdi
	movq $buffer,  %rsi # Load buffer (an ascii string of size 1, currently empty) into %rsi
	movq $1,	   %rdx # Load 1 into rdx
	movq $0,	   %rax # Load 0 into rax -> syscall, which is called

	# Constructs syscall to read
	# %rax=0 -> read(0, "\0", 1)
	# Reads one char from the fd=0 (sysin)
	syscall # Führt den syscall aus

	cmpq $0,	   %rax # Calculates the value from %rax - 0, sets ZF=1 if %rax == 0
	je   _end # Jump to _end when ZF=1 aka %rax=0 (read, returns nothing)

	movq $1,	   %rdi # Load 1 into %rdi
	movq $buffer,  %rsi # Load buffer into %rsi ($buffer contains the read char from above)
	movq $1,	   %rdx # Load 1 into %rdx
	movq $1,	   %rax # Load 1 into %rax

	# Constructs syscall to write
	# %rax=1 -> write(1, "<value from input>" 1)
	# Writes one char from the buffer to fd=1 (sysout)
	syscall

	jmp  _loop # Jumps back to _loop (infinite loop until
_end:
	movq $0,	   %rdi # Load 0 into %rdi
	movq $60,	   %rax # Loads 60 into %rax (syscall for exit)
	syscall # Calls the exit syscall with exit code 0
.section .data
buffer: .ascii "\0"
