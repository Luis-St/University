#include <stdio.h>

int main() {
    int v7 = 0, v5 = 42, v3;
    int  *v1 = &v7, *v4 = &v5;
    int **v2 = &v1, **v6 = &v4;
    **v2 = *v4 * 2;
    *v1  = 11;
    **v6 = v7 * 4;
    v3   = v7 + v5;
    printf("v7: %d\n", v7);
    printf("v5: %d\n", v5);
    printf("v3: %d\n", v3);
    return 0;
}
