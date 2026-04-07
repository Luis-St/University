#include <stdio.h>
#include <math.h>

int main(void) {
    int v[] = {10, 20, 3, 20, 12, 18, 13, 6, 5, 6, 5, 11, 6, 13, 14, 9, 6, 10, 8, 16};
    int n = sizeof(v) / sizeof(v[0]);

    int   max    = v[0];
    int   min    = v[0];
    float summe  = 0.0f;
    float sumRez = 0.0f;
    float sumLog = 0.0f;
    /*
     * HINWEIS: Das Produkt aller 20 Werte (~3.6 * 10^23) überschreitet
     * sowohl int (2^31-1 ~ 2.1*10^9) als auch long long (2^63-1 ~ 9.2*10^18).
     * Deshalb wird das geometrische Mittel über den Logarithmus berechnet:
     *   geo = exp( (1/n) * M ln(xi) )
     * Das ist mathematisch äquivalent zu n sqrt(x1 * x2 * ... * xn), aber ohne Overflow.
     */

    int i = 0;
    while (i < n) {
        int x = v[i];

        if (x > max) max = x;
        if (x < min) min = x;

        summe  += (float)x;
        sumRez += 1.0f / (float)x;
        sumLog += logf((float)x);

        i++;
    }

    float arithmetisch  = summe / (float)n;
    float geometrisch   = expf(sumLog / (float)n);
    float harmonisch    = (float)n / sumRez;

    printf("Anzahl Elemente : %d\n",   n);
    printf("Maximum         : %d\n",   max);
    printf("Minimum         : %d\n",   min);
    printf("Summe           : %.3f\n", summe);
    printf("Arithm. Mittel  : %.3f\n", arithmetisch);
    printf("Geom.   Mittel  : %.3f\n", geometrisch);
    printf("Harmon. Mittel  : %.3f\n", harmonisch);

    return 0;
}
