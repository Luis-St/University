#include <iostream>

using namespace std;

struct Point;
struct Date;
bool before(const Date &a, const Date &b);
void printDateDefault(Date date);
void printDatePointer(Date *date);
void printDateAddress(Date &date);
void task1();
void task2();
void task3();
void task4();
void task5();
void task6_1();
void task6_2();
void task7();

struct Point {
	int x;
	int y;
};

void task1() {
	Point point1 = {100, 50};
	Point point2 = {50, 100};
	Point result = {point1.x + point2.x, point1.y + point2.y};
	cout << "Sum of the x values: " << result.x << endl;
	cout << "Sum of the y values: " << result.y << endl;
}

void task2() {
	Point points[50];
	srand(time(0));
	for (int i = 0; i < 50; ++i) {
		points[i] = {rand() % 100, rand() % 100};
	}
	int minX, minY ;
	int x = 101, y = 101;
	for (int i = 0; i < 50; ++i) {
		if (points[i].x < x) {
			minX = i;
			x = points[i].x;
		}
		if (points[i].y < y) {
			minY = i;
			y = points[i].y;
		}
	}
	cout << "Position of the point with smallest x value: " << minX << " (" << points[minX].x << "|" << points[minX].y << ")" << endl;
	cout << "Position of the point with smallest y value: " << minY << " (" << points[minY].x << "|" << points[minY].y << ")" << endl;
}

enum Month {
	JANUARY = 1, FEBRUARY = 2, MARCH = 3, APRIL = 4, MAY = 5, JUNE = 6, JULY = 7, AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12
};

struct Date {
	int day;
	Month month;
	int year;
};

void printDateDefault(Date date) {
	cout << "Date: " << date.day << "." << date.month << "." << date.year << endl;
}

void printDatePointer(Date *date) {
	cout << "Date: " << date->day << "." << date->month << "." << date->year << endl;
}

void printDateAddress(Date &date) {
	cout << "Date: " << date.day << "." << date.month << "." << date.year << endl;
}

void task3() {
	Date date = {24, NOVEMBER, 2022};
	printDateDefault(date);
	printDatePointer(&date);
	printDateAddress(date);
}

bool before(const Date &a, const Date &b) {
	if (b.year < a.year) {
		return true;
	} else if (b.month < a.month) {
		return true;
	} else {
		return b.day < a.day;
	}
}

void task4() {
	Date a = {24, NOVEMBER, 2022};
	Date b = {25, NOVEMBER, 2022};
	if (before(a, b)) {
		cout << "Date b (" << b.day << "." << b.month << "." << b.year << ") is before date a (" << a.day << "." << a.month << "." << a.year << ")" << endl;
	} else {
		cout << "Date b (" << b.day << "." << b.month << "." << b.year << ") is not before date a (" << a.day << "." << a.month << "." << a.year << ")" << endl;
	}
}

void task5() {
	int n = ((-4 * -6) - (6 % 4)); // First * and % then -
	cout << "N: " << n << endl;
	int m = (((3 * 3) >> 2) << (2 * 2)); // First * and >> then <<
	cout << "M: " << m << endl;
	bool b = ((true == false) || ((~false == true) && (2 == 2))); // First == and && then ||
	cout << "B: "<< boolalpha << b << endl;
}

void task6_1() {
	int result = 0;
	for (int i = 101; i < 1000; ++i) {
		if (i % 2 != 0) {
			result += i;
		}
	}
	cout << "The sum of the odd numbers is: " << result << endl;
}

void task6_2() {
	int i = 101, result = 0;
	while (i < 1000) {
		if (i % 2 != 0) {
			result += i;
		}
		++i;
	}
	cout << "The sum of the odd numbers is: " << result << endl;
}

void task7() {
	int numbers[5];
	for (int i = 20; i > 15; --i) {
		numbers[20 - i] = i % 3;
	}
	for (int i = 0; i < 5; ++i) {
		// 2, 1, 0, 2, 1
		// 3, 1, 3, 2, 6
		// 9, 1, 12, 2, 18
		// 19, 1, 13, 2, 20
		// 21, 1, 14, 2, 22
		for (int j = 5; j > 0; j = j - 2) {
			numbers[5 - j] = numbers[i / 2] + numbers[j - 1];
		}
		// 36, 1, 28, 2, 64
	}
}

int main(int argc, char *argv[]) {
	task7();
}
