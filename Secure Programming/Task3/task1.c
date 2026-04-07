#include <stdio.h>

int main(void) {
    int alter = 17;

    if (alter >= 18) {
        printf("Die Person ist volljährig (Alter: %d Jahre).\n", alter);
    } else {
        printf("Die Person ist NICHT volljährig (Alter: %d Jahre).\n", alter);
    }

    return 0;
}
