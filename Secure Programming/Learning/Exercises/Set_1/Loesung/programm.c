#include <stdio.h>

int main() {
    unsigned char a = 128;
    unsigned char b = 0x40;
    unsigned char c = 'A';
    unsigned char d = a + b + c;
    printf("d ist %d\n", d);
    return 0;
}
