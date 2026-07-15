# Syscall Lookup Reference (x86-64 Linux)

## `ausyscall` — find the syscall name from its number

```bash
ausyscall <number>
```

Example:
```bash
ausyscall 1
# write
```

- Converts a raw syscall number (found in `rax` before a `syscall` instruction) into its name.
- Native to your machine's architecture (x86-64 here).

## `man 2 <name>` — find the parameters for that syscall

```bash
man 2 write
```

- Section `2` of the man pages covers syscalls specifically (as opposed to section `1` for shell commands or section `3` for C library functions).
- Shows the C prototype, e.g.:
  ```c
  ssize_t write(int fd, const void *buf, size_t count);
  ```
- Map the parameters onto syscall registers **in order**:

  | Param position | Register |
  |---|---|
  | 1st | `rdi` |
  | 2nd | `rsi` |
  | 3rd | `rdx` |
  | 4th | `r10` (not `rcx`!) |
  | 5th | `r8` |
  | 6th | `r9` |

## Typical workflow

1. Stop in GDB right before the `syscall` instruction.
2. Check `rax` → run `ausyscall <rax value>` → get the name.
3. Run `man 2 <name>` → get the parameter list and order.
4. Inspect the relevant registers (`rdi`, `rsi`, `rdx`, `r10`, `r8`, `r9`) with `i r`.
5. Dereference pointer arguments if needed:
   ```gdb
   x/s $rdi   # string
   x/8xb $rdi # raw bytes
   ```
