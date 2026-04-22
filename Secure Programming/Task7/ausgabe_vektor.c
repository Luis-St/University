#include <stdio.h>


int main(void) {
    int v[] = {1, 2, 3};
    int n = sizeof(v) / sizeof(v[0]);

    for (int i = 0; i < n; i++)
        printf("v[%d]=%d\n", i, v[i]);

    return 0;
}
