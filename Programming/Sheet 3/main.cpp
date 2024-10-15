#include <stdio.h>
#include <string>
#include <cmath>
#include <bitset>
#include <iostream>

using namespace std;

bool isLeapYear(int year);
void task1();
void task2();
void task3_1();
void task3_2();
int task4();
void task5();
void task6();

bool isLeapYear(int year) {
	return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}

void task1() {
	int year;
	cout << "Enter a year: ";
	cin >> year;
	if (isLeapYear(year)) {
		cout << "Year " << year << "is a leap year" << endl;
	} else {
		cout << "Year " << year << "is not a leap year" << endl;
	}
}

void task2() {
	int number;
	cout << "Enter a number: ";
	cin >> number;
	if (255 >= number && number >= 0) {
		cout << "Number " << number << " in Binaerian representation: " << bitset<8>(number).to_string().c_str() << endl;
	} else {
		cout << "The number can (theoretically) only be between 0 and 255" << endl;
	}
}

void task3_1() {
	long long EAN = 938546951484; // result should be 0
	long long number = (EAN % 10 * 3 + (EAN / 10) % 10 + (EAN / 100) % 10 * 3 + (EAN / 1000) % 10 + (EAN / 10000) % 10 * 3 + (EAN / 100000) % 10 + (EAN / 1000000) % 10 * 3 + (EAN / 10000000) % 10 + (EAN / 100000000) % 10 * 3 + (EAN / 1000000000) % 10 + (EAN / 10000000000) % 10 * 3 + (EAN / 100000000000) % 10) % 10;
	if (number != 0) {
		number = 10 - number;
	}
	cout << "The check digit for the EAN number " << EAN << " is: " << number << endl;
}

void task3_2() {
	long long ISBN = 999725971; // result should be X
	string x = to_string(ISBN);
	int number = (int(x[0] - 48) + int(x[1] - 48) * 2 + int(x[2] - 48) * 3 + int(x[3] - 48) * 4 + int(x[4] - 48) * 5 + int(x[5] - 48) * 6 + int(x[6] - 48) * 7 + int(x[7] - 48) * 8 + int(x[8] - 48) * 9) % 11;
	if (number == 10) {
		cout << "The check digit for the ISBN number " << ISBN << " is: X"<< endl;
	} else {
		cout << "The check digit for the ISBN number " << ISBN << " is: " << number << endl;
	}
}

int task4() {
	int number;
	cout << "Enter a number: ";
	cin >> number;
	if (0 > number) {
		cout << "Number less than 0, result is -1" << endl;
		return -1;
	} else if (number == 0) {
		cout << "Number is equal to 0, result is 0" << endl;
		return 0;
	} else {
		cout << "Number greater than 0, result is 1" << endl;
		return 1;
	}
}

void task5() {
    int p, q;
	cout << "Enter value of p: ";
	cin >> p;
	cout << "Enter value of q: ";
	cin >> q;
	double f = (double) p / 2;
	double d = (f * f) - q;
	if (d < 0) {
		cout << "The result " << d << "of the task does not correspond to a real number, task not solvable" << endl;
	} else {
        double x1 = -(p / 2.0) + sqrt(d);
        double x2 = -(p / 2.0) - sqrt(d);
		cout << "Result 1: " << x1 << endl;
		cout << "Result 2: " << x2 << endl;
	}
}

void task6() {
	int month, year;
	cout << "Enter month (as number): ";
	cin >> month;
	cout << "Enter a year: ";
	cin >> year;
	int days = 0;
	switch (month) {
		case 1: days = 31; break;
		case 2: days = isLeapYear(year) ? 29 : 28; break;
		case 3: days = 31; break;
		case 4: days = 30; break;
		case 5: days = 31; break;
		case 6: days = 30; break;
		case 7: days = 31; break;
		case 8: days = 31; break;
		case 9: days = 30; break;
		case 10: days = 31; break;
		case 11: days = 30; break;
		case 12: days = 31; break;
	}
	if (12 >= month >= 1) {
		cout << "The month " << month << " has in the year  " << year << " " << days << " days" << endl;
	} else {
		cout << "For the month a number between 0 and 12 can be entered" << endl;
	}
}

int main(int argc, char* argv[]) {
	task6();
}
