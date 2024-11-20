#include "task7.h"
#include <cassert>
#include <iostream>
#include <string>

#include "City.h"
#include "Position.h"

void lsv7::runTask7() {
	lsv7::test_sort();
	lsv7::positionTest();
	lsv7::positionCompareTest();
	lsv7::testConsoleOutput();
}

void lsv7::sort(int *numbers, const int size) {
	for (int i = 1; i < size; ++i) {
		const int key = numbers[i];
		int j = i - 1;
		while (j >= 0 && numbers[j] > key) {
			numbers[j + 1] = numbers[j];
			j = j - 1;
		}
		numbers[j + 1] = key;
	}
}

void lsv7::test_sort(int *numbers, const int size) {
	lsv7::sort(numbers, size);
	for (int i = 0; i < size - 1; ++i) {
		assert(numbers[i] <= numbers[i + 1]);
	}
}

void lsv7::test_sort() {
	int numbers0[] = {1, 2, 3, 4, 5};
	lsv7::test_sort(numbers0, 5);

	int numbers1[] = {5, 4, 3, 2, 1};
	lsv7::test_sort(numbers1, 5);

	int numbers2[] = {2, 6, 3, 1, 5, 4, 7};
	lsv7::test_sort(numbers2, 7);

	int numbers3[] = {1, 1, 1, 1, 1, 1, 1};
	lsv7::test_sort(numbers3, 7);
}

void lsv7::positionTest() {
	std::string mordor = "Mordor";
	const lsv7::Position position(mordor, 47, 11);

	assert(position.getName() == mordor);
	assert(position.getX() == 47);
	assert(position.getY() == 11);
	mordor[0] = 'X'; // Cannot assign to readonly type const char
	assert(position.getName() == "Mordor");
}

void lsv7::positionCompareTest() {
	const std::string mordor = "Mordor";
	const lsv7::Position position(mordor, 47, 11);
	assert(position == position);
	const lsv7::Position position_name("zordor", 47, 11);
	const lsv7::Position position_x(mordor, 48, 11);
	const lsv7::Position position_y(mordor, 47, 12);
	assert(position < position_name);
	assert(position < position_x);
	assert(position < position_y);
	assert(position_name > position);
	assert(position_x > position);
	assert(position_y > position);
}

void lsv7::sort(lsv7::Position *positions, const int size) {
	for (int i = 1; i < size; ++i) {
		lsv7::Position key = positions[i];
		int j = i - 1;
		while (j >= 0 && positions[j] > key) {
			positions[j + 1] = positions[j];
			j = j - 1;
		}
		positions[j + 1] = key;
	}
}

void lsv7::testConsoleOutput() {
	const lsv7::Position position("Mordor", 47, 11);
	std::cout << position << std::endl;

	std::cout << std::endl;

	const std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	const lsv7::City city("Mordor", 47, 11, pois, 3);
	std::cout << city << std::endl;
}
