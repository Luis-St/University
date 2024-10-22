#include "task2.h"
#include <iostream>
#include <cassert>

#include "Position.h"

void lsv2::runTask2() {
	lsv2::toIntTest();
	lsv2::decodeTest();
	lsv2::positionTest();
}

void lsv2::toInt(const char c, int &theInt) {
	if (c < '0' || c > '9') {
		return;
	}
	theInt = c - '0';
}

void lsv2::toIntTest() {
	int theInt;
	lsv2::toInt('0', theInt);
	assert(theInt == 0);
	lsv2::toInt('1', theInt);
	assert(theInt == 1);
	lsv2::toInt('a', theInt); // Invalid input -> stays the same
	assert(theInt == 1);
	lsv2::toInt('9', theInt);
	assert(theInt == 9);

	int number = -1;
	toInt('0', number);
	assert(number==0);
	number = -1;
	toInt('9', number);
	assert(number==9);
	number = -1;
	toInt('x', number);
	assert(number==-1);
}

int lsv2::decode(const std::string &line) {
	int firstDigit = -1, lastDigit = -1;
	for (int i = 0; line[i] != '\0'; ++i) {
		int currentDigit = -1;
		toInt(line[i], currentDigit);
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

void lsv2::decodeTest() {
	const std::string lines[] = {
		"1abc2",
		"pqr3stu8vwx",
		"a1b2c3d4e5f",
		"treb7uchet"
	};
	constexpr int expected[]{12, 38, 15, 77};
	for (int i = 0; i < 4; ++i) {
		assert(lsv2::decode(lines[i]) == expected[i]);
	}
	try {
		lsv2::decode("no digit");
		assert(false);
	} catch (std::invalid_argument &) {}
}

void lsv2::positionTest() {
	lsv2::Position pos1;
	assert(pos1.getName().empty());
	assert(pos1.getX() == 0);
	assert(pos1.getY() == 0);

	pos1.set("Position2", 10, 20);
	assert(pos1.getName() == "Position2");
	assert(pos1.getX() == 10);
	assert(pos1.getY() == 20);

	lsv2::Position pos2;
	pos2.set("Position3", 30, 40);
	assert(pos2.getName() == "Position3");
	assert(pos2.getX() == 30);
	assert(pos2.getY() == 40);
}
