#include <iostream>
#include <regex>
#include <windows.h>
#include <cstring>

using namespace std;

string toBinary(int number);
void task1();
void task2();
void task3();
void task4();
void task5();
void task6();
void task7();
void task8();

void task1() {
	int array[20], even = 0, odd = 0;
	srand(time(0));
	for (int i = 0; i < 20; ++i) {
		array[i] = rand();
	}
	for (int i = 0; i < 20; ++i) {
		cout << array[i] << endl;
	}
	for (int i : array) {
		if (i % 2 == 0) {
			even += i;
		} else {
			odd += i;
		}
	}
	cout << "Value of even numbers: " << even << endl;
	cout << "Value of the odd numbers: " << odd << endl;
}

void task2() {
	int array[10][10];
	srand(time(0));
	for (int i = 0; i < 10; ++i) {
		for (int j = 0; j < 10; ++j) {
			array[i][j] = rand() % 50;
		}
	}
	int input;
	cout << "Enter the number to search for: ";
	cin >> input;
	cout << endl;
	int containsCount = 0;
	for (int i = 0; i < 10; ++i) {
		for (int j = 0; j < 10; ++j) {
			 if (array[i][j] == input) {
				 ++containsCount;
			 }
		}
	}
	HANDLE color = GetStdHandle(STD_OUTPUT_HANDLE);
	cout << "The number " << input << " is " << containsCount << " times present" << endl;
	cout << endl;
	for (int i = 0; i < 10; ++i) {
		for (int j = 0; j < 10; ++j) {
			if (array[i][j] == input) {
				SetConsoleTextAttribute(color, 10);
			} else {
				SetConsoleTextAttribute(color, 7);
			}
			if (array[i][j] < 10) {
				cout << "0" << array[i][j] << " ";
			} else {
				cout << array[i][j] << " ";
			}
		}
		cout << endl;
	}
	SetConsoleTextAttribute(color, 7);
}

string toBinary(int number) {
	if (number == 0) {
		return "0";
	} else if (number == 1) {
		return "1";
	} else if (number % 2 == 0) {
		return toBinary(number / 2) + "0";
	} else {
		return toBinary(number / 2) + "1";
	}
}

void task3() {
	int number = 0;
	cout << "Enter a number: ";
	cin >> number;
	string str = toBinary(number);
	int binaryArray[str.length()];
	for (int i = 0; i < str.length(); ++i) {
		binaryArray[i] = str[i] - '0';
	}
	cout << "The Binaer number of " << number << " is: ";
	for (int i : binaryArray) {
		cout << i;
	}
	cout << endl;
}

void task4() {
	string str = "";
	cout << "Enter a string: ";
	cin >> str;
	cout << "The string has the length " << str.length() << endl;
	cout << "The string has the length " << strlen(str.c_str()) << endl;
}

void task5() {
	string str1 = "", str2 = "";
	cout << "Enter first string: ";
	getline(cin, str1);
	cout << "Enter second string: ";
	getline(cin, str2);
	if (str1.length() != str2.length()) {
		cout << "1" << endl;
	} else {
		bool equals = true;
		for (int i = 0; i < str1.length(); ++i) {
			if (str1[i] != str2[i]) {
				equals = false;
				break;
			}
		}
		cout << (equals ? "0" : "1") << endl;
	}
}

void task6() {
	string str = "";
	cout << "Enter a string: ";
	getline(cin, str);
	string reverse = "";
	for (int i = str.length() - 1; 0 <= i; --i) {
		reverse += str[i];
	}
	cout << "Rotated string: " << reverse;
}

void task7() {
	string str1 = "";
	cout << "Enter a string: ";
	getline(cin, str1);
	cout << "String without vowels: ";
	for (int i = 0; i < str1.length(); ++i) {
		char c = str1[i];
		if (c != 'a' && c != 'A' && c != 'e' && c != 'E' && c != 'i' && c != 'I' && c != 'o' && c != 'O' && c != 'u' && c != 'U') {
			cout << c;
		}
	}
	cout << endl;
}

void task8() {
	string str1 = "", str12, str2 = "";
	cout << "Enter first string: ";
	getline(cin, str1);
	cout << "Enter second string: ";
	getline(cin, str2);
	str12 = str1;
	cout << "String after the append: " << str1.append(str2) << endl;
	string str = "";
	for (int i = 0; i < str12.length(); ++i) {
		str += str12[i];
	}
	for (int i = 0; i < str2.length(); ++i) {
		str += str2[i];
	}
	cout << "String after the append: " << str << endl;
}

int main(int argc, char* argv[]) {
	task8();
}
