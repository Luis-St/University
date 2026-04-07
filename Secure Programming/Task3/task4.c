#include <stdio.h>

int main(void) {
    int n = 10;

    int summe_while = 0;
    int i = 1;
    while (i <= n) {
        summe_while += i * i;
        i++;
    }
    printf("While-Schleife: Summe i² (i=1..%d) = %d\n", n, summe_while);

    int summe_for = 0;
    for (int j = 1; j <= n; j++) {
        summe_for += j * j;
    }
    printf("For-Schleife  : Summe i² (i=1..%d) = %d\n", n, summe_for);

    return 0;
}
