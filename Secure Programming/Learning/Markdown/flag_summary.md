# x86-64 Flags Cheat Sheet (ZF, SF, CF, OF)

All flags below refer to the result of `cmpq src, dst`, which internally computes `dst - src` and discards the result, keeping only the flags.

## ZF — Zero Flag

**Set to 1 when:** the result is exactly zero → `dst == src`

```asm
movq $5, %rax
cmpq $5, %rax   # 5 - 5 = 0 → ZF=1
```

## SF — Sign Flag

**Set to 1 when:** the most significant bit of the result is 1 (result *looks* negative in two's complement)

```asm
movq $3, %rax
cmpq $5, %rax   # 3 - 5 = -2 → MSB=1 → SF=1
```

## CF — Carry Flag

**Set to 1 when:** an unsigned borrow occurred → treating both operands as unsigned, `dst < src`

```asm
movq $3, %rax
cmpq $5, %rax   # unsigned: 3 < 5 → borrow needed → CF=1
```

⚠️ CF only looks at raw bit patterns, not signed meaning. A "negative" number is a huge unsigned value, which can make CF results look surprising:

```asm
movq $-1, %rax     # bit pattern = 0xFFFF...FFFF (huge unsigned)
cmpq $1, %rax      # unsigned: huge - 1 → no borrow → CF=0
                   # even though -1 < 1 in signed terms!
```

## OF — Overflow Flag

**Set to 1 when:** the signed result overflowed the representable range — the true mathematical result doesn't fit in signed bits, causing the sign to flip incorrectly.

Occurs during `dst - src` when:
- `dst` and `src` have **different signs**, AND
- the result's sign **doesn't match** `dst`'s sign

```asm
movq $0x7FFFFFFFFFFFFFFF, %rax   # MAX signed 64-bit
cmpq $-1, %rax                   # rax - (-1) = rax + 1
                                 # true result = MAX+1, wraps to negative
                                 # → OF=1
```

## Quick Reference Table

| Flag | Set when | Interpretation |
|------|----------|----------------|
| **ZF** | result == 0 | operands are equal |
| **SF** | MSB of result == 1 | result *looks* negative |
| **CF** | unsigned borrow occurred | `dst < src` (unsigned) |
| **OF** | signed overflow occurred | sign bit can't be trusted |

## Why Signed vs. Unsigned Jumps Differ

| Comparison type | Jump instructions | Flags used |
|---|---|---|
| Signed | `jg`, `jge`, `jl`, `jle` | `SF`, `OF` (and `ZF`) |
| Unsigned | `ja`, `jae`, `jb`, `jbe` | `CF` (and `ZF`) |
| Either | `je`, `jne`, `js`, `jns` | `ZF` or `SF` only |

Signed comparisons need `SF == OF` (or `!=`) rather than just `SF`, because if overflow happened, the raw sign bit lies about the true mathematical result. XOR-ing against OF corrects for that.

## Worked Example

```asm
movq $10, %rax
cmpq $3, %rax     # rax - 3 = 7
```

| Flag | Value | Why |
|------|-------|-----|
| ZF | 0 | result (7) isn't zero |
| SF | 0 | 7 is positive |
| CF | 0 | unsigned: 10 ≥ 3, no borrow |
| OF | 0 | no signed overflow, same sign region |

All flags agree here — no overflow or sign trickery, so signed and unsigned comparisons give the same answer. This is the "normal case"; exam trick questions usually involve negative numbers or values near 0 / max / min, where signed and unsigned results can diverge.