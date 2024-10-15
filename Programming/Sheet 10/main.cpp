#include <iostream>
#include "SafeArray.h"

using namespace std;

void task1();
void task2();
void task3();

void task1() {
	SafeArray array;
	const int value = 10;
	cout << "Start with setting the values of the safe array" << endl;
	for (int i = -1; i < 101; ++i) {
		if (!array.setAt(value, i)) {
			cout << "An error occurred when setting the value at position " << i << endl;
		}
	}
	cout << "Start with getting the values of the safe array" << endl;
	for (int i = -1; i < 101; ++i) {
		if (array.getAt(i) != value) {
			cout << "An error occurred when getting the value at position " << i << endl;
		}
	}
}

void task2() {
	const int value = 10;
	SafeArray array = SafeArray(value);
	array.setAt(0, 10); // Test
	cout << "Start with getting the default values of the safe array" << endl;
	for (int i = 0; i < 100; ++i) {
		if (array.getAt(i) != value) {
			cout << "An error occurred when getting the value at position " << i << endl;
		}
	}
}

void task3() {
	SafeArray array = SafeArray(73);
	array.setAt(34, 0);
	cout << "Value at 15: " << array.getAt(15) << endl;
	cout << "The smallest value of the SafeArray is: " << array.getMinimum() << endl;
	const int value = 10;
	array.setAt(value, 0, 100);
	cout << "Start with getting the values of the safe array" << endl;
	array.setAt(0, 10); // Test
	for (int i = 0; i < 100; ++i) {
		if (array.getAt(i) != value) {
			cout << "An error occurred when getting the value at position " << i << endl;
		}
	}
}

int main(int argc, char *argv[]) {
	task3();
}
