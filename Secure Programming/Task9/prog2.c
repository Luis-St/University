#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <time.h>

void split(char *birthday, int *year, int *month, int *day) {
	char *y  = strtok(birthday, "-");
	char *m = strtok(NULL, "-");
	char *d   = strtok(NULL, "-");

	*year = atoi(y);
	*month = atoi(m);
	*day = atoi(d);
}

int main() {
	printf("Enter birthday in formate yyyy-mm-dd (zero extended): \n");

	char birthday[10];
	size_t bytes = read(1, birthday, 10);
	if (bytes == -1) {
		perror("Fail to read birthday.\n");
		return -1;
	}

	printf("\n");
	int year;
	int month;
	int day;
	split(birthday, &year, &month, &day);

	printf("Year: %d\n", year);
	printf("Month: %d\n", month);
	printf("Day: %d\n", day);

	printf("\n");

	time_t now;
	time(&now);

	struct tm *t = localtime(&now);
	printf("Current year: %d\n", t->tm_year + 1900);
	printf("Current month: %d\n", t->tm_mon + 1);
	printf("Current day: %d\n", t->tm_mday);

	printf("\n");
	int yearDiff = (t->tm_year + 1900) - year;
	int monthDiff = (t->tm_mon + 1) - month;
	int dayDiff = (t->tm_mday) - day;
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


