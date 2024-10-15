#include <stdlib.h>
#include <iostream>

using namespace std;

void task1();
void task2_2();
void task2_3();
void task4();
void task5();
void task6();
void task7();
void task8();
void task9();

void task1() {
	int list[20];
	for (int i = 0; i < 20; i++) {
		list[i] = rand();
	}
	int min = list[0];
	for (int i = 1; i < 20; i++) {
		if (list[i] < min) {
			min = list[i];
		}
	}
	cout << "The minimum of" << endl;
	for (int i = 0; i < 20; i++) {
		cout << list[i] << endl;
	}
	cout << "is:" << endl << min << endl;
}

// ignore task2_1 due creating errors

void task2_2() {
	int list[20];
	for (int i = 0; i < 20; i++) {
		list[i] = rand();
	}
	int max = list[0];
	for (int i = 1; i < 20; i++) {
		if (max < list[i]) {
			max = list[i];
		}
	}
	cout << "The maximum of" << endl;
	for (int i = 0; i < 20; i++) {
		cout << list[i] << endl;
	}
	cout << "is:" << endl << max << endl;
}

void task2_3() {
	int list[40];
	for (int i = 0; i < 40; i++) {
		list[i] = rand();
	}
	int min = list[0];
	for (int i = 1; i < 40; i++) {
		if (list[i] < min) {
			min = list[i];
		}
	}
	cout << "The minimum of" << endl;
	for (int i = 0; i < 40; i++) {
		cout << list[i] << endl;
	}
	cout << "is:" << endl << min << endl;
}

// Ignore task 2.4, because ä, ö and ü are not ASCII characters

// Ignore task 2.5 because the numbers for index >20 are random

void task4() {
	// Shortest C++ program, it does nothing
}

void task5() {
	int n;
	n = 17 - 2 * 7 + 9 % 6;
	n = (17 - 2) * (7 + 9) % 6;
	n = (17 - 2) * ((7 + 9) % 6);
	n = ((((((17 - 2) * 7) - 9) * 7) + 9) % 6);
	n = 17 - (2 * (7 + (9 % 6)));
	n = 17 / (5 / 3) * 4;
	n = (17 / 5) * (5 / 17);
	cout << "The result is: " << n << endl;
	
	float f;
	f = 17.0 / (5 / 3) * 4;
	f = (17.0 / 5) * (5 / 17);
	f = 1 * (1.0 / 3) * 3;
	f = 1.5e2 * 1.5e2;
	f = 1.5e2 * 1.5e-2;
	f = 1.5e-2 * 1.5e-2;
	cout << "The result is: " << f << endl;
	
	char c;
	c = 'a' + 5;
	c = '0' + 9;
	c = '0' + 9 / 2;
	c = '0' + 9 - 2;
	c = '0' + '9';
	cout << "The result is: " << c << endl;
	
	bool b;
	c = '5';
	b = (c >= '0' && c <= '9');
	b = (c >= 0 && c <= 9);
	b = (c >= '0' || c <= '9');
	b = (c >= 0 || c <= 9);
	cout << "The result is: " << b << endl;
	
	int m = 44;
	m = 44 >> 2;
	m = 44 << 1;
	m = 1 << 10;
	m = 1 << 32;
	cout << "The result is: " << m << endl;
}

void task6() {
	int x, y;
	cout << "Some arithmetic ..." << endl;
	cout << "Enter first number: ";
	cin >> x;
	cout << "Enter second number: " << endl;
	cin >> y;
	cout << endl;
	cout << x << " + " << y << " is " << x + y << endl;
	cout << x << " - " << y << " is " << x - y << endl;
	cout << x << " * " << y << " is " << x * y << endl;
	cout << x << " / " << y << " is " << x / y << endl;
	cout << x << " % " << y << " is " << x % y << endl;
}

void task7() {
	int i;
	long l;
	float f;
	double d;
	d = 100 / 3;
	f = d;
	l = f;
	i = l;
	d = 100 / 3.0;
	f = d;
	l = f;
	i = l;
	d = (float) 100 / 3;
	f = d;
	l = f;
	i = l;
	// All zero due they are not initialized
}

void task8() {
	int x;
	cout << "Enter a number: ";
	cin >> x;
	cout << "Value x * 2 is " << (x << 1) << endl;
	cout << "Value x / 2 is " << (x >> 1) << endl;
}

void task9() {
	cout << "Size of a char:" << sizeof(char) << endl;
	cout << "Size of a short:" << sizeof(short) << endl;
	cout << "Size of a bool:" << sizeof(bool) << endl;
	cout << "Size of a int:" << sizeof(int) << endl;
	cout << "Size of a long:" << sizeof(long) << endl;
	cout << "Size of a float:" << sizeof(float) << endl;
	cout << "Size of a double:" << sizeof(double) << endl;
}

int main(int argc, char *argv[]) {
	task9();
}