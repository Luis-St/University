# C Cheat Sheet

## Data Types & Sizes (typical, 64-bit Linux)

| Type | Size | Range (signed) |
|---|---|---|
| `char` | 1 byte | -128 … 127 |
| `short` | 2 bytes | -32,768 … 32,767 |
| `int` | 4 bytes | -2,147,483,648 … 2,147,483,647 |
| `long` | 8 bytes | -9.2×10^18 … 9.2×10^18 |
| `long long` | 8 bytes | same as `long` |
| `float` | 4 bytes | ~7 significant digits |
| `double` | 8 bytes | ~15 significant digits |
| `unsigned` variant | same | 0 … 2× max |

`sizeof(type)` gives the size at compile/runtime (type `size_t`).

## Pointers

```c
int a = 5;
int *p = &a;      // p points to a
*p = 10;          // change value through pointer → a becomes 10
int **pp = &p;    // pointer to pointer

int arr[5] = {1,2,3,4,5};
int *pa = arr;    // array decays to pointer to first element
pa++;             // now points to arr[1] (pointer arithmetic)
*(pa + 2);        // == arr[3]
pa - arr;         // difference between two pointers → number of elements between them

void *vp;         // generic pointer, can't dereference without a cast
NULL;             // null pointer (from <stddef.h>/<stdlib.h>)
```

**Pointer vs. array:** `arr[i]` is equivalent to `*(arr + i)`.
A function parameter `int arr[]` is internally `int *arr` (size info is lost!).

### const combinations

```c
const int *p1;        // value is constant, pointer can change
int * const p2 = &a;  // pointer is constant, value can change
const int * const p3 = &a; // both constant
```
Reading tip: read right to left — `const` applies to whatever is to its left.

### Function pointers

```c
int add(int a, int b) { return a + b; }
int (*fp)(int, int) = add;
int result = fp(2, 3);       // == add(2, 3)
```

### Pointers to structs & multiple indirection

```c
struct Point *pp = &p1;
pp->x = 5;          // == (*pp).x = 5

int **matrix;       // e.g. for a dynamic 2D array
int ***ppp;          // theoretically arbitrarily deep, rarely used in practice
```

### Common pointer mistakes

- Dereferencing a `NULL` or uninitialized pointer
- Dangling pointer (pointing to already-freed memory)
- Off-by-one errors in pointer arithmetic
- Forgetting `&` with `scanf`

## Structs

```c
struct Point {
    int x;
    int y;
};

struct Point p1 = {1, 2};

typedef struct {
    int x, y;
} Point;            // no more "struct" keyword needed when using it

// nested structs
struct Rect {
    struct Point tl;
    struct Point br;
};
struct Rect r = {{0,0}, {10,10}};
r.tl.x = 5;
```

## Memory Management (`<stdlib.h>`)

```c
void *malloc(size_t size);          // uninitialized
void *calloc(size_t n, size_t size); // zero-initialized
void *realloc(void *ptr, size_t size);
void free(void *ptr);

int *arr = malloc(10 * sizeof(int));
if (arr == NULL) { /* error handling */ }
free(arr);
arr = NULL;   // avoid dangling pointer
```

**Classic mistakes:** double free, use-after-free, memory leak (no `free`), buffer overflow.

## Memory Regions

| Region | Content |
|---|---|
| Stack | local variables, grows/shrinks automatically |
| Heap | `malloc`/`free`, manually managed |
| Data/BSS | global & `static` variables |
| Text/Code | machine code, read-only |

## Strings (are `char` arrays, `\0`-terminated)

```c
char s[] = "Hello";        // 6 bytes incl. '\0', mutable
char *s2 = "Hello";        // pointer to string literal (read-only!)
char s3[10];                // uninitialized, no automatic '\0'

strlen(s);        // length without '\0'
strcpy(dst, src);  // copies including '\0', no bounds check!
strncpy(dst, src, n);  // safer, but may not null-terminate itself
strcat(dst, src);  // appends src to dst
strncat(dst, src, n);
strcmp(s1, s2);    // 0 if equal, <0 / >0 otherwise
strncmp(s1, s2, n);
strchr(s, 'a');    // pointer to first occurrence of 'a', else NULL
strstr(s, "ab");   // pointer to first occurrence of substring
strtok(s, ",");    // split string by delimiter (destroys original!)
```

### String ↔ Number

```c
int i = atoi("42");
double d = atof("3.14");
long l = strtol("42", NULL, 10);   // with error handling, base=10
```

### Manual iteration over a string

```c
for (char *c = s; *c != '\0'; c++) {
    // *c is the current character
}
```

## printf / scanf

### Format specifiers

| Specifier | Type |
|---|---|
| `%d` / `%i` | int |
| `%u` | unsigned int |
| `%ld`, `%lld` | long, long long |
| `%f` | float/double (printf) |
| `%lf` | double (only needed for scanf) |
| `%c` | char |
| `%s` | string |
| `%p` | pointer (address) |
| `%x` / `%X` | hexadecimal (lower/upper) |
| `%o` | octal |
| `%%` | literal `%` |

### Width, precision, flags

```c
printf("%5d\n", 42);      // right-aligned, min. 5 chars wide: "   42"
printf("%-5d|\n", 42);    // left-aligned: "42   |"
printf("%05d\n", 42);     // zero-padded: "00042"
printf("%.2f\n", 3.14159); // 2 decimal places: "3.14"
printf("%8.2f\n", 3.14);  // width 8, 2 decimal places
printf("%x\n", 255);      // "ff"
printf("%#x\n", 255);     // "0xff"
```

### scanf details

```c
int x;
scanf("%d", &x);          // don't forget & (must pass a pointer!)

char buf[100];
scanf("%99s", buf);       // limit width → prevents overflow!
scanf(" %c", &c);         // leading space before %c skips whitespace/newline

int matched = scanf("%d %d", &a, &b); // return value = number of successfully read values
```

**Watch out:** `scanf("%s", ...)` reads until the next whitespace, no bounds check without a width specifier → buffer overflow possible.

## File Descriptors (Low-Level I/O, `<unistd.h>`, `<fcntl.h>`)

A file descriptor is an `int` assigned starting from 0: `0` = stdin, `1` = stdout, `2` = stderr.

```c
#include <fcntl.h>
#include <unistd.h>

int fd = open("file.txt", O_RDONLY);
int fd2 = open("out.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
if (fd == -1) { /* error, check errno */ }

char buf[128];
ssize_t n = read(fd, buf, sizeof(buf));   // reads up to 128 bytes, returns bytes read (0 = EOF)
ssize_t w = write(fd2, buf, n);           // writes n bytes

close(fd);
close(fd2);
```

### Important `open` flags

| Flag | Meaning |
|---|---|
| `O_RDONLY` | read only |
| `O_WRONLY` | write only |
| `O_RDWR` | read & write |
| `O_CREAT` | create the file if it doesn't exist |
| `O_TRUNC` | truncate existing content |
| `O_APPEND` | append to the end |

`read`/`write`/`open`/`close` are system calls (unbuffered, go straight to the kernel) — no buffering, every call is a direct transition into the kernel.

### Return values & error handling

```c
ssize_t n = read(fd, buf, size);
if (n < 0) {
    perror("read");   // prints errno text
} else if (n == 0) {
    // EOF reached
}
```

### lseek – changing the file position

```c
off_t pos = lseek(fd, 0, SEEK_SET);   // jump to the beginning
lseek(fd, 0, SEEK_END);               // jump to the end
lseek(fd, -10, SEEK_CUR);             // 10 bytes back from current position
```

## Important Headers

| Header | Content |
|---|---|
| `<stdio.h>` | printf, scanf, FILE |
| `<stdlib.h>` | malloc, free, exit, atoi |
| `<string.h>` | strlen, strcpy, memcpy |
| `<unistd.h>` | read, write, close, lseek |
| `<fcntl.h>` | open, flags (O_RDONLY etc.) |
| `<math.h>` | sqrt, pow (link with `-lm`) |
| `<stdbool.h>` | bool, true, false |
| `<stdint.h>` | int32_t, uint8_t etc. (fixed widths) |

## Compiling with gcc / clang

### Basic workflow

```bash
gcc -o program file.c      # compile & link in one step
./program                    # run

clang -o program file.c     # clang is largely flag-compatible with gcc
```

### The 4 translation steps individually

```bash
gcc -E file.c -o file.i     # 1. preprocessing (resolve macros, #include)
gcc -S file.i -o file.s     # 2. compile to assembly
gcc -c file.s -o file.o     # 3. assemble to object code
gcc file.o -o program       # 4. link into an executable
```

### Important flags

| Flag | Meaning |
|---|---|
| `-o <name>` | name of the output file |
| `-c` | compile only, don't link (produces `.o`) |
| `-Wall` | enable common warnings |
| `-Wextra` | additional warnings |
| `-g` | embed debug symbols (needed for GDB!) |
| `-O0`, `-O1`, `-O2`, `-O3` | optimization level (O0 = none, recommended for debugging) |
| `-std=c11`, `-std=c17` | set the C standard |
| `-I<path>` | additional include path for headers |
| `-L<path>` | additional path for libraries |
| `-l<name>` | link against a library, e.g. `-lm` for `<math.h>` |
| `-static` | link statically instead of dynamically |
| `-fsanitize=address` | AddressSanitizer (catches buffer overflows, use-after-free) |

### Typical debug build

```bash
gcc -Wall -Wextra -g -O0 -o program file.c
gdb ./program
```

### Multiple files

```bash
gcc -c a.c -o a.o
gcc -c b.c -o b.o
gcc a.o b.o -o program
```

### Useful for analysis

```bash
objdump -d program          # show disassembly
gcc -S -O0 file.c            # inspect assembly output directly (file.s)
```
