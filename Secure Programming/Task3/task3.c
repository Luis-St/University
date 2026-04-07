#include <stdio.h>

int main(void) {
    int n = 10;

    int a = 0;
    int b = 1;
    int i = 1;

    printf("Fibonacci-Folge (n = %d):\n", n);

    while (i <= n) {
        printf("F(%d) = %d\n", i, a);

        int temp = a + b;
        a = b;
        b = temp;
        i = i + 1;
    }

    return 0;
}
