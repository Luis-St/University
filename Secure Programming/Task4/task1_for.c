#include <stdio.h>
#include <math.h>

int main(void) {
    int v[] = {10, 20, 3, 20, 12, 18, 13, 6, 5, 6, 5, 11, 6, 13, 14, 9, 6, 10, 8, 16};
    int n = sizeof(v) / sizeof(v[0]);

    int   max    = *v;
    int   min    = *v;
    float summe  = 0.0f;
    float sumRez = 0.0f;
    float sumLog = 0.0f;

    /*
     * p läuft von v[0] bis v[n-1].
     * (v + i) ist die Adresse des i-ten Elements, *(v + i) der Wert.
     */
    for (int i = 0; i < n; i++) {
        int *p = v + i;
        int  x = *p;

        if (x > max) max = x;
        if (x < min) min = x;

        summe  += (float)x;
        sumRez += 1.0f / (float)x;
        sumLog += logf((float)x);
    }

    float arithmetisch = summe / (float)n;
    float geometrisch  = expf(sumLog / (float)n);
    float harmonisch   = (float)n / sumRez;

    printf("Anzahl Elemente : %d\n",   n);
    printf("Maximum         : %d\n",   max);
    printf("Minimum         : %d\n",   min);
    printf("Summe           : %.3f\n", summe);
    printf("Arithm. Mittel  : %.3f\n", arithmetisch);
    printf("Geom.   Mittel  : %.3f\n", geometrisch);
    printf("Harmon. Mittel  : %.3f\n", harmonisch);

    return 0;
}
