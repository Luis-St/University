#include <stdio.h>

int main() {
	unsigned short us[] = {0, 255, 65535};
	printf("us[1]: %04X\n", us[1]);

	us[1] = 85;
    printf("us[1]: %04X\n", us[1]);

    us[0 + 1] = 0x00;
    printf("us[1]: %04X\n", us[1]);

    // Der Pointer puc muss vom Type char sein, +3 würde nämlich bei short keinen sinn ergeben (wäre auserhalb des arrays)
    // Wie genau dieser aber verwendet wird keine Ahnung, deshalb hier +1 mit Wert 0xff00
    // Gibt das gleiche ergbnis aus xD
	*(&us + 1) = 0xff00;
    printf("us[1]: %04X\n", us[1]);

    return 0;
}



