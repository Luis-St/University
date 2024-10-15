#include <iostream>

using namespace std;

bool validateNumber(int number);
bool isAlreadyPresent(int number, const int *numbers, bool debug);
void getUserNumbers(int *userNumbers);
void getNumbers(int *numbers);
void printNumbers(int *userNumbers, int *lotteryNumbers);
void task3_1();
void task3_2();
void task3_3();

// Task 1 was already done when creating sheet 9

// Task 2 was already done when creating sheet 9

bool validateNumber(int number) {
	if (number < 1 || 49 < number) {
		cout << "The entered number is out of range [1,49), try again" << endl;
		return false;
	}
	return true;
}

bool isAlreadyPresent(int number, const int *numbers, bool debug) {
	if (number == 0) {
		return true;
	} else {
		for (int i = 0; i < 6; ++i) {
			if (numbers[i] == number) {
				if (debug) {
					cout << "The number " << number << " has already been entered" << endl;
				}
				return true;
			} else if (numbers[i] == 0) {
				break;
			}
		}
		return false;
	}
}

void getUserNumbers(int *userNumbers) {
	cout << "Enter your 6 numbers between 1 and 49 below: " << endl;
	int i = 0;
	while (i < 6) {
		int j;
		cout << (i + 1) << ": ";
		cin >> j;
		if (validateNumber(j) && !isAlreadyPresent(j, userNumbers, true)) {
			userNumbers[i] = j;
			i++;
		}
	}
}

void getNumbers(int *numbers) {
	srand(time(nullptr));
	int i = 0;
	while (i < 6) {
		int j = rand() % 49 + 1;
		if (!isAlreadyPresent(j, numbers, false)) {
			numbers[i] = j;
			i++;
		}
	}
}

void printNumbers(int *userNumbers, int *lotteryNumbers) {
	cout << "Your entered numbers: ";
	for (int i = 0; i < 6; ++i) {
		cout << userNumbers[i] << (i == 5 ? "" : ", ");
	}
	cout << endl;
	cout << "Lottery numbers: ";
	for (int i = 0; i < 6; ++i) {
		cout << lotteryNumbers[i] << (i == 5 ? "" : ", ");
	}
	cout << endl;
}

void task3_1() {
	int userNumbers[6] = {0, 0, 0, 0, 0, 0};
	getUserNumbers(userNumbers);
	cout << endl;
	int lotteryNumbers[6] = {0, 0, 0, 0, 0, 0};
	getNumbers(lotteryNumbers);
	printNumbers(userNumbers, lotteryNumbers);
	int correct = 0;
	for (int i = 0; i < 6; ++i) {
		if (userNumbers[i] == lotteryNumbers[i]) {
			++correct;
		}
	}
	cout << "You have guessed " << correct << " number correctly" << endl;
}

void task3_2() {
	int userNumbers[6];
	getNumbers(userNumbers);
	int lotteryNumbers[6];
	getNumbers(lotteryNumbers);
	printNumbers(userNumbers, lotteryNumbers);
	int correct = 0;
	for (int i = 0; i < 6; ++i) {
		if (userNumbers[i] == lotteryNumbers[i]) {
			++correct;
		}
	}
	cout << "You have guessed " << correct << " number correctly" << endl;
}

void task3_3() {
	srand(time(nullptr));
	int atLeast = 0, tries = 0;
	int userNumbers[6];
	getNumbers(userNumbers);
	for (; atLeast < 5;) {
		int correct = 0;
		for (int i = 0; i < 6; ++i) {
			if (userNumbers[i] == rand() % 49 + 1) {
				++correct;
			}
		}
		if (3 <= correct) {
			++atLeast;
			cout << "Again at least 3 correct " << atLeast << " with " << tries << " attempts" << endl;
		}
		++tries;
	}
	cout << "For 5 times at least 3 correct numbers " << tries << " attempts were needed" << endl;
}

int main(int argc, char *argv[]) {
	task3_1();
}
