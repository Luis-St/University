#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	if (1 >= argc || argc > 2) {
		printf("Required at least one parameter\n");
		return -1;
	}

	char *file = argv[1];
	int fd = open(file, O_RDONLY);
	printf("File %s opened\n", file);

	char buffer[100];

	while (1) {
		size_t bytes = read(fd, buffer, sizeof(buffer));
		if (bytes == -1) {
			perror("Fail to read chars");
			break;
		}
		if (bytes == 0) {
			break;
		}

		write(STDOUT_FILENO, buffer, bytes);
	}

	close(fd);
	printf("File %s closed\n", file);

	return 0;
}
