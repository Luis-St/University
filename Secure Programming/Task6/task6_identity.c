#include <stdio.h>

#define N 3

int main(void) {
    float A[N][N] = {
        {9.0f, 4.0f, 2.0f},
        {4.0f, 3.0f, 6.0f},
        {8.0f, 5.0f, 3.0f}
    };
    float I[N][N] = {
        {1.0f, 0.0f, 0.0f},
        {0.0f, 1.0f, 0.0f},
        {0.0f, 0.0f, 1.0f}
    };
    float C[N][N] = {{0}};

    /* Matrizenmultiplikation C = A * I */
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                C[i][j] += A[i][k] * I[k][j];

    printf("Matrix A:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            printf("%6.1f", A[i][j]);
        printf("\n");
    }

    printf("Einheitsmatrix I:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            printf("%6.1f", I[i][j]);
        printf("\n");
    }

    printf("Ergebnis C = A * I:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            printf("%6.1f", C[i][j]);
        printf("\n");
    }

    return 0;
}
