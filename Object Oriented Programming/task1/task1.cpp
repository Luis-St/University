#include "task1.h"
#include <iostream>
#include <cassert>

void runTask1() {
	alternatingSumOfDigitsTest();
	toIntTest();
	decodeTest();
}

int alternatingSumOfDigits(const char *number) {
	if (number[0] == '-') {
		throw std::invalid_argument("Negative numbers are not supported");
	}
	int sum = 0;
	int i = 0;
	while (number[i] != '\0') {
		if (i % 2 == 0) {
			sum += number[i] - '0';
		} else {
			sum -= number[i] - '0';
		}
		i++;
	}
	return sum;
}

void alternatingSumOfDigitsTest() {
	assert(alternatingSumOfDigits("471") == -2);
	assert(alternatingSumOfDigits("4711") == -3);
	assert(alternatingSumOfDigits("123456") == -3);
	assert(alternatingSumOfDigits("0") == 0);
	assert(alternatingSumOfDigits("9") == 9);
	try {
		decode("-10");
	} catch (const std::invalid_argument &e) {
		assert(true);
	}

	// All inputs that do contain non-digit characters are invalid -> error
}

void toInt(const char c, int *theInt) {
	if (c < '0' || c > '9') {
		return;
	}
	*theInt = c - '0';
}

void toIntTest() {
	int theInt;
	toInt('0', &theInt);
	assert(theInt == 0);
	toInt('1', &theInt);
	assert(theInt == 1);
	toInt('a', &theInt); // Invalid input -> stays the same
	assert(theInt == 1);
	toInt('9', &theInt);
	assert(theInt == 9);
}

int decode(const char *line) {
	int firstDigit = -1, lastDigit = -1, currentDigit;
	for (int i = 0; line[i] != '\0'; ++i) {
		currentDigit = -1;
		toInt(line[i], &currentDigit);
		if (currentDigit != -1) {
			if (firstDigit == -1) {
				firstDigit = currentDigit;
			}
			lastDigit = currentDigit;
		}
	}
	if (firstDigit == -1) {
		throw std::invalid_argument("Missing digit");
	}
	return firstDigit * 10 + lastDigit;
}

void decodeTest() {
	assert(decode("1xyz2") == 12);
	assert(decode("abc3pqr8ijk") == 38);
	assert(decode("x1y2z3v4w5q") == 15);
	assert(decode("hello7world") == 77);
	try {
		decode("no digits");
	} catch (const std::invalid_argument &e) {
		assert(true);
	}
}

