#include <iostream>
#include "stack.h"

using namespace std;

int getUserInput();
void bubbleSort(int *array, int length);
void task1();
void task2();
void task3();
void task5();
void task6();

void task1() {
	stack stack;
	stack.clear();
	stack.push('a');
	stack.push('b');
	stack.push('c');
	cout << "Stack top: " << stack.top() << endl; // c
	stack.pop();
	cout << "Stack size: " << stack.size() << endl; // 2
	stack.pop();
	cout << "Stack top: " << stack.top() << endl; // a
	stack.pop();
	cout << "Stack empty: " << boolalpha << stack.empty() << endl;
}

void task2() {
	string str;
	cout << "Enter bracketing: ";
	getline(cin, str);
	if (str.empty()) {
		cout << "No bracketing available" << endl;
	} else if (str.length() % 2 != 0) {
		cout << "It is a non balanced bracketing" << endl;
	} else {
		stack stack;
		bool error = false;
		for (int i = 0; i < str.length(); ++i) {
			char c = str[i];
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.top() == '(') {
					stack.pop();
				} else {
					error = true;
					break;
				}
			} else if (c == '}') {
				if (stack.top() == '{') {
					stack.pop();
				} else {
					error = true;
					break;
				}
			} else if (c == ']') {
				if (stack.top() == '[') {
					stack.pop();
				} else {
					error = true;
					break;
				}
			} else {
				cout << "Character '" << c << "' is skipped because it is not a bracket" << endl;
			}
		}
		if (error) {
			cout << "It is a non balanced bracketing" << endl;
		} else {
			cout << "It is a balanced bracketing" << endl;
		}
	}
}

int getUserInput() {
	int userInput = 0;
	cout << "Enter your estimated number: ";
	cin >> userInput;
	return userInput;
}

void task3() {
	srand(time(0));
	int tries = 0, number = rand() % 100;
	do {
		int userInput = getUserInput();
		++tries;
		if (number < userInput) {
			cout << "The number to guess is smaller than your estimate" << endl;
		} else if (userInput < number) {
			cout << "The number to guess is larger than your estimate" << endl;
		} else {
			cout << "You guessed the number, you needed " << tries << " attempts for this" << endl;
			break;
		}
	} while (true);
}

void task5() {
	// Missing function implementation -> undefined reference to 'function name'
	// Duplicate function implementation -> redefinition of 'function name' & 'function name' previously defined here
	// Without cpp implementation file -> No such file or directory
}

void bubbleSort(int *array, int length) {
	int j;
	for (int i = 0; i < length; ++i) {
		for (j = 0; j < length - i - 1; ++j) {
			if (array[j] > array[j + 1]) {
				int k = array[j];
				array[j] = array[j + 1];
				array[j + 1] = k;
			}
		}
	}
	for (int i = 0; i < length; ++i) {
		cout << array[i] << (i == length - 1 ? "" : ", ");
	}
	cout << endl;
}

void task6() {
	int array [5] = {1, 4, 3, 5, 2};
	bubbleSort(array, 5);
}

int main(int argc, char *argv[]) {
	task6();
}
