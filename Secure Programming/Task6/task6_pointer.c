#include <stdio.h>

#define ROWS_A 2
#define COLS_A 3
#define COLS_B 2

int main(void) {
    float A[ROWS_A][COLS_A] = {
        {3.0f, 2.0f, 1.0f},
        {1.0f, 0.0f, 2.0f}
    };
    float B[COLS_A][COLS_B] = {
        {1.0f, 2.0f},
        {0.0f, 1.0f},
        {4.0f, 0.0f}
    };
    float C[ROWS_A][COLS_B] = {{0}};

    /* Matrizenmultiplikation C = A * B mit Zeigeroperationen */
    for (int i = 0; i < ROWS_A; i++)
        for (int j = 0; j < COLS_B; j++)
            for (int k = 0; k < COLS_A; k++)
                *(*(C + i) + j) += *(*(A + i) + k) * *(*(B + k) + j);

    printf("Matrix A:\n");
    for (int i = 0; i < ROWS_A; i++) {
        for (int j = 0; j < COLS_A; j++)
            printf("%6.1f", *(*(A + i) + j));
        printf("\n");
    }

    printf("Matrix B:\n");
    for (int i = 0; i < COLS_A; i++) {
        for (int j = 0; j < COLS_B; j++)
            printf("%6.1f", *(*(B + i) + j));
        printf("\n");
    }

    printf("Ergebnis C = A * B:\n");
    for (int i = 0; i < ROWS_A; i++) {
        for (int j = 0; j < COLS_B; j++)
            printf("%6.1f", *(*(C + i) + j));
        printf("\n");
    }

    return 0;
}
