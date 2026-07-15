#include <stdio.h>

unsigned long laenge(const char *s) {
    unsigned long i = 0;
    while (s[i] != 0) {
        i++;
    }
    return i;
}

int main() {
    char t[] = "Hallo";
    printf("Laenge: %lu\n", laenge(t));
    return 0;
}
