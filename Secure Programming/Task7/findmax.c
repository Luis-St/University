#include <stdio.h>


int find_max(int *v, int n) {
    int max = *v;
    for (int i = 1; i < n; i++) {
        if (*(v + i) > max)
            max = *(v + i);
    }
    return max;
}

int main(void) {
    int v[] = {3, 7, 1, 9, 4};
    int n = sizeof(v) / sizeof(v[0]);

    int max = find_max(v, n);
    printf("Max: %d\n", max);

    return 0;
}
