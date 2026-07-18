# GDB Cheat Sheet — `-g` compiled, source file missing

Compile command this applies to:
```bash
gcc -no-pie -fno-stack-protector -g -o prog prog.c
```
(source `prog.c` deleted after compiling — debug *metadata* is embedded in the binary, but GDB can't display source *text*)

---

## Check what GDB knows

```gdb
info source          # shows recorded source filename/path GDB is looking for (will fail to find it)
info sources          # lists all compilation units GDB has debug info for
```

## Variables & types — still work (real names, no guessing needed)

```gdb
info locals            # local variable names, types, and current values
info args               # function argument names, types, and values
whatis var               # quick type name of a variable/expression
ptype var                 # full type definition (expands structs/arrays)
p &var                     # address of a variable
p &arr[N]                   # address of array element N
p var                        # print current value
```

## Stepping — line-accurate, but no visible source text

```gdb
step / s        # step one source line, entering calls (works correctly, just nothing to display)
next / n        # step one source line, stepping over calls
stepi / si       # step one instruction (always works, source or not)
nexti / ni        # step one instruction, over calls
```

## Source display — broken, use disassembly instead

```gdb
list             # FAILS — tries to open prog.c, "No such file or directory"
layout src         # FAILS — source pane stays empty
layout asm           # USE THIS INSTEAD — disassembly view works regardless of source
layout regs            # pair with layout asm for live register tracking
```

## Breakpoints — symbolic, still fully functional

```gdb
break main             # breakpoint by function name (works — comes from debug info)
break prog.c:12          # breakpoint by file:line (accepted even though file is missing — GDB
                           # just needs the line-number table, not the actual text)
break *0xADDR              # breakpoint by raw address (still an option, -no-pie keeps it stable)
```

## Call stack — function names intact

```gdb
bt          # backtrace with real function names
bt full      # backtrace + locals shown per frame
frame N       # jump to a specific frame
```

## Disassembly — your main window into "what's happening"

```gdb
disassemble           # current function
disassemble main        # specific function by name
x/20i $pc                 # 20 instructions from current position
set disassembly-flavor intel   # switch AT&T -> Intel syntax if preferred
```

## Memory inspection — for arrays / raw values

```gdb
x/s ADDR         # string at address
x/xb ADDR         # 1 byte, hex
x/xh ADDR          # 1 halfword (2B), hex — e.g. unsigned short
x/xw ADDR           # 1 word (4B), hex — e.g. int
x/xg ADDR             # 1 giant word (8B), hex — e.g. long/pointer
x/Nxh ADDR              # N halfwords in one dump — good for scanning a short[] array
```

## Analysing arrays

With `-g`, array variables have real names/types, so this is much easier than the fully-stripped case.

```gdb
whatis arr           # e.g. "type = unsigned short [5]" — element type + length
ptype arr              # same, fully expanded
p arr                    # prints ALL elements at once, e.g. {1, 2, 3, 4, 5}
p arr[2]                   # single element by index
p &arr                       # address of the whole array (== address of arr[0])
p &arr[2]                      # address of a specific element
p sizeof(arr)                    # total array size in bytes
p sizeof(arr[0])                   # size of one element (e.g. 2 for unsigned short)
```

**Dumping raw memory instead of using the symbol** (useful for cross-checking, or if the array is inside a struct):

```gdb
x/Nxh &arr        # N = element count, x = hex, h = halfword (2B) — for unsigned short[]
x/Nxb &arr          # same but b = byte, for char[]/unsigned char[]
x/Nxw &arr            # same but w = word (4B), for int[]
x/Ndh &arr              # decimal instead of hex — often more readable for plain values
```

**Casting a raw address to a typed pointer** (handy once you know the base address but don't have/trust the symbol, e.g. after computing it manually from `$rbp`):

```gdb
set $p = (unsigned short *)&arr
p $p[0]
p $p[1]
p *($p + 2)          # same as $p[2] — pointer arithmetic works too
```

**Looping over elements automatically** (avoids typing `p arr[N]` one by one):

```gdb
p *arr@5              # print 5 elements starting at arr[0] as an array-slice — very useful!
p *(arr+2)@3            # print 3 elements starting at arr[2]
```

`@` is GDB's "artificial array" operator — `*ptr@count` treats `count` elements starting at `ptr` as one array to print. Works on any pointer/address, symbol or raw.

**No debug info fallback** (if you ever go back to a stripped build): find the base offset on the stack by spotting a run of same-width `mov` instructions at consecutive offsets (e.g. `movw` stepping by 2 bytes = `unsigned short[]`), then use `x/Nxh $rbp-OFFSET` as covered above.

## Registers

```gdb
info registers       # all general-purpose registers
p $rax / p $eax / p $al   # register at 64/32/8-bit width
p/x $rax                    # print in hex
p $pc                         # current instruction pointer
```

---

## TL;DR — what's different from a fully stripped binary

| Task | Fully stripped (no `-g`) | `-g` + source deleted |
|---|---|---|
| Variable names/types | ❌ must infer from instructions (`movzbl` etc.) | ✅ `info locals`, `whatis`, `ptype` work |
| Function names | ❌ often just addresses | ✅ real names via `bt`, `break`, `info functions` |
| Source line mapping | ❌ none | ✅ `step`/`next` advance correctly per line |
| Actual source text | ❌ never available | ❌ still unavailable — `list`/`layout src` fail |
| Primary tool for "what's executing" | `disassemble` / `layout asm` | `disassemble` / `layout asm` (same) |