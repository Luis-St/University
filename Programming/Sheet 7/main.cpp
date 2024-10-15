#include <iostream>
#include <string>
#include <stdlib.h>

using namespace std;

int calc(int i, int j, char op);
bool isBlank(string str);
long ggt_1(long x, long y);
long ggt_2(long x, long y);
bool prime(int x);
void stat(const char *s);
void add(int *n, int m);
void task1();
void task2(int argc, char *argv[]);
void task3_1();
void task3_2();
void task4();
void task5();
void task6();
void task7();



int calc(int i, int j, char op) {
	switch (op) {
		case '+':
			return i + j;
		case '-':
			return i - j;
		case '*':
			return i * j;
		case '/':
			return i / j;
		default:
			return -999;
	}
}

void task1() {
	int i, j;
	cout << "Enter first number: ";
	cin >> i;
	cout << "Enter second number: ";
	cin >> j;
	cout << "Enter a operator: ";
	char op;
	cin >> op;
	cout << "The result of " << i << " " << op << " " << j << " is: " << calc(i, j, op) << endl;
}

bool isBlank(string str) {
	for (int i = 0; i < str.length(); ++i) {
		if (!isspace(str[i])) {
			return false;
		}
	}
	return true;
}

void task2(int argc, char *argv[]) {
	if (argc < 5) {
		cout << "Too many arguments" << endl;
	} else if (argc > 5) {
		cout << "Too few arguments" << endl;
	} else {
		string task = argv[1];
		if (task != "Aufgabe2" && task != "Task2") {
			cout << "Task '" << task << "' does not exist" << endl;
		} else {
			int number1 = atoi(argv[2]);
			int number2 = atoi(argv[3]);
			string stringOp = argv[4];
			if (stringOp.length() != 1) {
				cout << stringOp << " is not a valid arithmetic operator" << endl;
			} else {
				char op = stringOp[0];
				cout << "The result of " << number1 << " " << op << " " << number2 << " is: " << calc(number1, number2, op) << endl;
			}
		}
	}
}

long ggt_1(long x, long y) {
	if (x == y) {
		return x;
	}
	while (x != y) {
		if (y < x) {
			x = x - y;
		} else if (x < y) {
			y = y - x;
		} else {
			break;
		}
	}
	return x;
}

void task3_1() {
	long x, y;
	cout << "Enter first number: ";
	cin >> x;
	cout << "Enter second number: ";
	cin >> y;
	cout << "Smallest divisor from " << x << " and " << y << " is " << ggt_1(x, y) << endl;
}

long ggt_2(long x, long y) {
	if (x == y) {
		return x;
	} else if (y < x) {
		return ggt_2(x - y, y);
	} else if (x < y) {
		return ggt_2(x, y - x);
	}
}

void task3_2() {
	long x, y;
	cout << "Enter first number: ";
	cin >> x;
	cout << "Enter second number: ";
	cin >> y;
	cout << "Smallest divisor from " << x << " and " << y << " is " << ggt_2(x, y) << endl;
}

bool prime(int x) {
	for (int i = 2; i < x; ++i) {
		if (x % i == 0) {
			return false;
		}
	}
	return true;
}

void task4() {
	int x;
	cout << "Enter a number: ";
	cin >> x;
	cout << "The number " << x << " is " << (prime(x) ? "a" : "not a") << " prime number" << endl;
}

void stat(const char *s) {
	int chars = 0;
	int words = s[0] == '\0' ? 0 : 1;
	char c, cl = '\0';
	while (true) {
		c = s[chars];
		if (c == '\0') {
			break;
		} else if (c == ' ' && cl != ' ') {
			++words;
		}
		cl = c;
		++chars;
	}
	cout << "Number of characters: " << chars << endl;
	cout << "Number of words: " << words << endl;
}

void task5() {
	string str;
	cout << "Enter a string: ";
	getline(cin, str);
	stat(str.c_str());
}

void add(int *n, int m) {
	*n += m;
}

void task6() {
	int n, m;
	cout << "Enter first number: ";
	cin >> n;
	cout << "Enter second number: ";
	cin >> m;
	add(&n, m);
	cout << "The result is: " << n << endl;
}

int main(int argc, char *argv[]) {
	task6();
}
