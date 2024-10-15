#include "task2.h"
#include <iostream>
#include <cassert>

#include "Position.h"

void ls::runTask2() {
	ls::toIntTest();
	ls::decodeTest();
	ls::positionTest();
}

void ls::toInt(const char c, int &theInt) {
	if (c < '0' || c > '9') {
		return;
	}
	theInt = c - '0';
}

void ls::toIntTest() {
	int theInt;
	ls::toInt('0', theInt);
	assert(theInt == 0);
	ls::toInt('1', theInt);
	assert(theInt == 1);
	ls::toInt('a', theInt); // Invalid input -> stays the same
	assert(theInt == 1);
	ls::toInt('9', theInt);
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

int ls::decode(const std::string &line) {
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

void ls::decodeTest() {
	const std::string lines[] = {
		"1abc2",
		"pqr3stu8vwx",
		"a1b2c3d4e5f",
		"treb7uchet"
	};
	constexpr int expected[]{12, 38, 15, 77};
	for (int i = 0; i < 4; ++i) {
		assert(ls::decode(lines[i]) == expected[i]);
	}
	try {
		ls::decode("no digit");
		assert(false);
	} catch (std::invalid_argument &) {}
}

void ls::positionTest() {
	ls::Position pos1;
	assert(pos1.getName().empty());
	assert(pos1.getX() == 0);
	assert(pos1.getY() == 0);

	pos1.set("Position2", 10, 20);
	assert(pos1.getName() == "Position2");
	assert(pos1.getX() == 10);
	assert(pos1.getY() == 20);

	ls::Position pos2;
	pos2.set("Position3", 30, 40);
	assert(pos2.getName() == "Position3");
	assert(pos2.getX() == 30);
	assert(pos2.getY() == 40);
}
