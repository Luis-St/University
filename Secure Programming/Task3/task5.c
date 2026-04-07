#include <stdio.h>

int main(void) {
    printf("=== Multiplikationstabelle (For-Schleife) ===\n\n");

    printf("   |");
    for (int k = 1; k <= 10; k++) printf(" %3d", k);
    printf("\n---+");
    for (int k = 1; k <= 10; k++) printf("----");
    printf("\n");

    for (int i = 1; i <= 10; i++) {
        printf("%2d |", i);
        for (int j = 1; j <= 10; j++) {
            printf(" %3d", i * j);
        }
        printf("\n");
    }

    printf("\n");

    printf("=== Multiplikationstabelle (While-Schleife) ===\n\n");

    printf("   |");
    int k = 1;
    while (k <= 10) { printf(" %3d", k); k++; }
    printf("\n---+");
    k = 1;
    while (k <= 10) { printf("----"); k++; }
    printf("\n");

    int i = 1;
    while (i <= 10) {
        printf("%2d |", i);
        int j = 1;
        while (j <= 10) {
            printf(" %3d", i * j);
            j++;
        }
        printf("\n");
        i++;
    }

    return 0;
}
