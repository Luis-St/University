#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char *buf = malloc(16);
    if (!buf) {
        return 1;
    }
    strcpy(buf, "Hallo");
    printf("vorher:  %s\n", buf);

    free(buf);
    printf("nachher: %s\n", buf);

    return 0;
}
