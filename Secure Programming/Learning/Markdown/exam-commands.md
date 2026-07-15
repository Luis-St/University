# Exam Cheat Sheet: Reading Assembly & Analyzing Binaries

All commands for Ubuntu (requires `build-essential` and `gdb`).
Uses **AT&T syntax** (GAS) — `movq $60, %rax`, source before destination.

---

## Getting an overview (unknown binary)

```bash
file programm         # 32/64-bit? stripped? statically/dynamically linked?
strings programm      # all printable strings (prompts, format strings, ...)
nm programm           # symbols / function names (empty if stripped)
readelf -a programm   # ELF structure: sections, symbols, headers
```

---

## Tasks 1 & 3: Disassembling with objdump

```bash
objdump -d programm       # disassemble, AT&T syntax (this is the default)
objdump -d -l programm    # additionally with line numbers
```

---

## Task 3: Analysis with GDB

Start:

```bash
gdb ./programm
```

Inside gdb — display (TUI):

```
layout asm        # assembly window
layout regs       # additionally show registers
```

(AT&T is gdb's default flavor, so no extra configuration is needed.)

Controlling execution:

```
break _start      # breakpoint at the entry label (or: break main)
run               # start the program
stepi   / si      # step one assembly instruction (steps into calls)
nexti   / ni      # step one instruction (steps over calls)
continue / c      # run until the next breakpoint
```

Inspecting:

```
disassemble _start       # disassemble a function/label (or: disassemble main)
info functions           # list known functions/labels
info registers           # all registers
print $rax               # single register / expression
x/8xw $rsp               # 8 words hex from the stack pointer (view memory)
```

---

## Practice: internalizing the C -> assembly mapping

Compile your own C programs and look at the assembly (AT&T is gcc's default):

```bash
gcc -O0 -g programm.c -o programm     # -O0 keeps assembly close to the code
objdump -d programm                   # disassemble the result

gcc -S -O0 -fverbose-asm programm.c   # produces programm.s in AT&T syntax with comments
```

`-O0` = no optimization (clean 1:1 mapping). `-fverbose-asm` adds comments showing
which variable currently lives where.

---

## Practice: assemble your own .s file and step through it

Your `.s` files are already AT&T/GAS syntax, so gcc assembles them natively:

```bash
gcc -o programm datei.s     # assembly file to binary
gdb ./programm              # then step through as above and check your hypothesis
```

For a freestanding `_start` program (no libc, like your example), skip the C runtime:

```bash
gcc -nostdlib -o programm datei.s     # no standard library / no main required
```

Self-check: read the assembly -> form a hypothesis -> run it in gdb ->
observe registers/memory -> compare against your hypothesis.

---

## Pattern cheat sheet (AT&T — recognize these in assembly)

| C construct        | Assembly pattern (AT&T)                                        |
|--------------------|---------------------------------------------------------------|
| Function prologue  | `push %rbp` / `mov %rsp, %rbp`  (src, dest!)                  |
| Function epilogue  | `leave` / `pop %rbp` followed by `ret`                       |
| if / else          | `cmp` or `test` followed by `je`/`jne`/`jl`/`jg`/...         |
| Loop               | conditional/unconditional jump **backwards** (`jmp`, `jne`)  |
| Return value       | in `%rax` (or `%eax`)                                        |
| First 6 int args   | `%rdi, %rsi, %rdx, %rcx, %r8, %r9` (in this order)          |

---

## AT&T syntax reminders

```
movq $60, %rax        # mov SRC, DEST  -> put constant 60 into rax
movq -0x4(%rbp), %eax # move value at address (rbp - 4) into eax
```

- Source comes first, destination last (`mov src, dest`).
- Registers are prefixed with `%`, immediate constants with `$`.
- Suffix denotes operand size: `b` (byte, 8), `w` (word, 16), `l` (long, 32),
  `q` (quad, 64) — e.g. `movq` = 64-bit move.
- Memory operands: `offset(base, index, scale)`, e.g. `8(%rbp)` or `(%rax,%rcx,4)`.
