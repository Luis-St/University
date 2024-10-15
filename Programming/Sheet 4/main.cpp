#include <iostream>
#include <bitset>

using namespace std;

int fib(int number);
double squareRoot(double x, int a);
void task1();
void task2_1();
void task2_2();
void task3();
void task4();
void task5();
void task6();

int fib(int number) {
	if (number == 0 || number == 1) {
		return 1;
	} else {
		return fib(number - 1) + fib(number - 2);
	}
}

void task1() {
	cout.precision(17);
	for (int i = 0; i <= 20; ++i) {
		int fibNumber = fib(i);
		cout << "Fib(" << i << ")" << " = " << fibNumber;
		if (i > 0) {
			cout << "\t\t" << (double) fibNumber / (double) fib(i - 1) << endl;
		} else {
			cout << endl;
		}
	}
}

double squareRoot(double x, int a) {
	return 0.5 * (x + a / x);
}

void task2_1() {
	cout.precision(17);
	int a = 2;
	cout << "Enter number for root calculation: ";
	cin >> a;
	double pre = 1.0;
	for (int i = 1; i <= 10; ++i) {
		pre = squareRoot(pre, a);
		cout << "Calculation for value " << i << " is: " << pre << endl;
	}
}

void task2_2() {
	cout.precision(17);
	int a = 2;
	cout << "Enter number for root calculation: ";
	cin >> a;
	double x = 1.0;
	double pre = 1.0;
	double diff = 1.0;

	do {
		pre = x;
		x = squareRoot(x, a);
		cout << "Value is: " << x << endl;
		diff = pre - x;
		if (diff < 0) {
			diff *= -1.0;
		}
	} while (diff > 0.000001);
}

void task3() {
	for (int i = 0; i != 10; i = i + 1) { // from 0 to 9 in steps of 1
		cout << i << endl;
	}
	cout << endl;
	for (int i = 10; i > 0; i = i - 1) { // from 10 to 1 in steps of 1
		cout << i << endl;
	}
	cout << endl;
	for (int i = 1; i <= 15; i = i + 3) { // from 1 to 13 in steps of 3
		cout << i << endl;
	}
	cout << endl;
	for (int i = 1; i != 15; i = i + 3) { // from 1 to infinity in steps of 3
		cout << i << endl;
	}
	cout << endl;
	for (int i = 1; i == 15; i = i + 3) { // never
		cout << i << endl;
	}
	cout << endl;
	for (char c = '5'; c <= '9'; c = c + 2) { // from 5 to 9 in steps of 2
		cout << c << endl;
	}
}

void task4() {
	for (int row = 20; row >= 1; --row) {
		for (int column = 1; column <= row; ++column) {
			cout << "*";
		}
		cout << endl;
	}
}

void task5() {
	for (int i = 1; i <= 9; ++i) {
		for (int j = 0; j < 5; ++j) {
			cout << i * 10 + j * 2 << " ";
		}
		cout << endl;
	}
}

void task6() {
	int number;
	cout << "Enter a number: ";
	cin >> number;
	if (255 >= number && number >= 0) {
		string bin = bitset<8>(number).to_string();
		cout << "The Binaer number of " << number << " is " << bin << endl;
		cout << "The rotated Binaer number is: ";
		for (int i = bin.length() - 1; i >= 0; --i) {
			cout << bin[i];
		}
		cout << endl;
	} else {
		cout << "The number can (theoretically) only be between 0 and 255" << endl;
	}
}

int main(int argc, char* argv[]) {
	task6();
}

