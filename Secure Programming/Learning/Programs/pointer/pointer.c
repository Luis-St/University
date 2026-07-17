#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

void findMin(int *numbers, int size, int *min) {
	*min = -1;

	for (int* i = numbers; i < numbers + size; i++) {
		int value = *i;
		if (value <= -1) {
			continue;
		}

		if (value < *min || *min == -1) {
			*min = value;
		}
	}
}

int main() {
	int numbers[] = {8, 7, 66, 4, 45, 10, 6, 9, 4, 7, 45, 90, 5};
	int size = sizeof(numbers) / sizeof(*numbers);
	int min = -1;

	findMin(numbers, size, &min);

	printf("Min value: %d\n", min);
	return 0;
}
