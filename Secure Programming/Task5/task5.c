#include <stdio.h>
#include <math.h>

float calc_max(int *v, int n) {
    float max = (float)*v;
    for (int i = 1; i < n; i++) {
        float x = (float)*(v + i);
        if (x > max) max = x;
    }
    return max;
}

float calc_min(int *v, int n) {
    float min = (float)*v;
    for (int i = 1; i < n; i++) {
        float x = (float)*(v + i);
        if (x < min) min = x;
    }
    return min;
}

float calc_summe(int *v, int n) {
    float summe = 0.0f;
    for (int i = 0; i < n; i++)
        summe += (float)*(v + i);
    return summe;
}

float calc_arithmetisch(int *v, int n) {
    return calc_summe(v, n) / (float)n;
}

float calc_geometrisch(int *v, int n) {
    float sumLog = 0.0f;
    for (int i = 0; i < n; i++)
        sumLog += logf((float)*(v + i));
    return expf(sumLog / (float)n);
}

float calc_harmonisch(int *v, int n) {
    float sumRez = 0.0f;
    for (int i = 0; i < n; i++)
        sumRez += 1.0f / (float)*(v + i);
    return (float)n / sumRez;
}

int main(void) {
    int v[] = {10, 20, 3, 20, 12, 18, 13, 6, 5, 6, 5, 11, 6, 13, 14, 9, 6, 10, 8, 16};
    int n = sizeof(v) / sizeof(v[0]);

    /* Funktionspointer-Array: alle Funktionen haben die Signatur float (*)(int*, int) */
    float (*ops[6])(int *, int) = {
        calc_max,
        calc_min,
        calc_summe,
        calc_arithmetisch,
        calc_geometrisch,
        calc_harmonisch
    };
    const char *namen[6] = {
        "Maximum         ",
        "Minimum         ",
        "Summe           ",
        "Arithm. Mittel  ",
        "Geom.   Mittel  ",
        "Harmon. Mittel  "
    };

    /* Auswahl ist festcodiert: 0 = alle ausgeben */
    int auswahl = 0;

    printf("Anzahl Elemente : %d\n", n);

    switch (auswahl) {
        case 1: printf("%s: %.3f\n", namen[0], ops[0](v, n)); break;
        case 2: printf("%s: %.3f\n", namen[1], ops[1](v, n)); break;
        case 3: printf("%s: %.3f\n", namen[2], ops[2](v, n)); break;
        case 4: printf("%s: %.3f\n", namen[3], ops[3](v, n)); break;
        case 5: printf("%s: %.3f\n", namen[4], ops[4](v, n)); break;
        case 6: printf("%s: %.3f\n", namen[5], ops[5](v, n)); break;
        default:
            for (int i = 0; i < 6; i++)
                printf("%s: %.3f\n", namen[i], ops[i](v, n));
            break;
    }

    return 0;
}
