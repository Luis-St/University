#include <stdio.h>

int main() {
    unsigned short us[] = {0, 255, 65535};
    printf("us[1]: %04X\n", us[1]);

    us[1] = 85;
    printf("us[1]: %04X\n", us[1]);

    unsigned short *pus = &us[0];
    *(pus + 1) = 0x00;
    printf("us[1]: %04X\n", us[1]);

    unsigned char *puc = (unsigned char *) pus;
    *(puc + 3) = 0xff;
    printf("us[1]: %04X\n", us[1]);

    return 0;
}
