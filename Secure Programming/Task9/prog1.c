#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <time.h>

int main() {
	printf("Enter birthday in formate yyyy-mm-dd (zero extended): \n");

	char birthday[10];
	size_t bytes = read(1, birthday, 10);
	if (bytes == -1) {
		perror("Fail to read birthday.\n");
		return -1;
	}

	printf("\n");
	char *year  = strtok(birthday, "-");
	char *month = strtok(NULL, "-");
	char *day   = strtok(NULL, "-");

	printf("Year: %s\n", year);
	printf("Month: %s\n", month);
	printf("Day: %s\n", day);

	printf("\n");

	time_t now;
	time(&now);

	struct tm *t = localtime(&now);
	printf("Current year: %d\n", t->tm_year + 1900);
	printf("Current month: %d\n", t->tm_mon + 1);
	printf("Current day: %d\n", t->tm_mday);

	printf("\n");
	int yearDiff = (t->tm_year + 1900) - atoi(year);
	int monthDiff = (t->tm_mon + 1) - atoi(month);
	int dayDiff = (t->tm_mday) - atoi(day);
	printf("Year diff: %d\n", yearDiff);
	printf("Month diff: %d\n", monthDiff);
	printf("Day diff: %d\n", dayDiff);

	printf("\n");
	if (yearDiff >= 18) {
		printf("You are 18 years old!\n");
		return 0;
	} else {
		printf("You are not 18 years old!\n");
		return 0;
	}
}


