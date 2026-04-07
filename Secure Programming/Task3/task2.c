#include <stdio.h>
#include <time.h>

int ist_volljaehrig(int heute_tag, int heute_monat, int heute_jahr, int geb_tag, int geb_monat, int geb_jahr) {
    int alter = heute_jahr - geb_jahr;

    if (heute_monat < geb_monat ||
        (heute_monat == geb_monat && heute_tag < geb_tag)) {
        alter--;
    }

    return alter >= 18;
}

int main(void) {
    time_t jetzt = time(NULL);
    struct tm *heute = localtime(&jetzt);

    int h_tag   = heute->tm_mday;
    int h_monat = heute->tm_mon + 1;
    int h_jahr  = heute->tm_year + 1900;

    int g_tag   = 15;
    int g_monat = 4;
    int g_jahr  = 2007;

    printf("Heutiges Datum : %02d.%02d.%04d\n", h_tag, h_monat, h_jahr);
    printf("Geburtsdatum   : %02d.%02d.%04d\n", g_tag, g_monat, g_jahr);

    if (ist_volljaehrig(h_tag, h_monat, h_jahr, g_tag, g_monat, g_jahr)) {
        printf("Ergebnis: Die Person ist volljährig.\n");
    } else {
        printf("Ergebnis: Die Person ist NICHT volljährig.\n");
    }

    return 0;
}
