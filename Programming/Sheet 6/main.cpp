#include <iostream>
#include <cstring>

using namespace std;

int powerOf2(int number);
void rotate(const char *s);
void zp(int a, int b);
void contains(const char *s, const char *m);
void task2();
void task5();
void task6();
void task7();

/*void task1() {
	int *pi;
	int i = *pi;
	double *pd = &i;
	char ***c;
}*/

void task2() {
	int m = 9;
	int n = 10;
	int *pm = &m;
	int *pn = &n;
	int i = *pm;
	*pm = n;
	*pn = i;
	cout << "m = " << m << ", n = " << n << endl;
}

void task3() {
	int n = 3;
	float x = n;
	float y = 1;
	float *py = &y;
	*py = 2 * *py * x;
	int a[2] = {10, 11};
	int *b = a;
	int c = a[0] * *(b + 1);
	int d = c + *b;
	cout << "Y: " << y << endl; // 6
	cout << "C: " << c << endl; // 110
	cout << "D: " << d << endl; // 120
}

int powerOf2(int number) {
	return number * number;
}

void task4() {
	int number;
	cout << "Enter a number: ";
	cin >> number;
	cout << "Square number of " << number << " is " << powerOf2(number) << endl;
}

void rotate(const char *s) {
	cout << "Rotated string: ";
	for (int i = strlen(s); 0 <= i; --i) {
		cout << s[i];
	}
	cout << endl;
}

void task5() {
	string str;
	cout << "Enter a string: ";
	getline(cin, str);
	rotate(str.c_str());
}

void zp(int a, int b) {
	int i = 1;
	while (i <= b) {
		if (a <= i && i <= b) {
			cout << i << endl;
		}
		i *= 2;
	}
}

void task6() {
	int a = 0, b = 0;
	cout << "Enter the beginning of the interval: ";
	cin >> a;
	cout << "Enter the end of the intervals:";
	cin >> b;
	if (b < a) {
		cout << "The end of the interval cannot be greater than the beginning: " << b << " > " << a << endl;
	} else {
		zp(a, b);
	}
}

void contains(const char *s, const char *m) {
	int containsCount = 0;
	for (int i = 0; i < strlen(s); ++i) {
		bool flag = false;
		for (int j = 0; j < strlen(m); ++j) {
			int checkIndex = i + j;
			if (checkIndex < strlen(s) && s[checkIndex] == m[j]) {
				flag = true;
				continue;
			} else {
				flag = false;
				break;
			}
		}
		if (flag) {
			++containsCount;
		}
	}
	cout << "String " << containsCount << " times present" << endl;
}

void task7() {
	string str1 = "", str2 = "";
	cout << "Enter string to be checked: ";
	getline(cin, str1);
	cout << "Enter check string: ";
	getline(cin, str2);
	contains(str1.c_str(), str2.c_str());
}

int main(int argc, char* argv[]) {
	task7();
}
