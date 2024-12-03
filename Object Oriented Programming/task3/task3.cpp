#include "task3.h"
#include <cassert>
#include <string>

#include "Position.h"

void lsv3::runTask3() {
	lsv3::test_sort();
	lsv3::positionTest();
	lsv3::positionCompareTest();
}

void lsv3::sort(int *numbers, const int size) {
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

void lsv3::test_sort(int *numbers, const int size) {
	lsv3::sort(numbers, size);
	for (int i = 0; i < size - 1; ++i) {
		assert(numbers[i] <= numbers[i + 1]);
	}
}

void lsv3::test_sort() {
	int numbers0[] = {1, 2, 3, 4, 5};
	lsv3::test_sort(numbers0, 5);

	int numbers1[] = {5, 4, 3, 2, 1};
	lsv3::test_sort(numbers1, 5);

	int numbers2[] = {2, 6, 3, 1, 5, 4, 7};
	lsv3::test_sort(numbers2, 7);

	int numbers3[] = {1, 1, 1, 1, 1, 1, 1};
	lsv3::test_sort(numbers3, 7);
}

void lsv3::positionTest() {
	std::string mordor = "Mordor";
	const lsv3::Position position(mordor, 47, 11);

	assert(position.getName() == mordor);
	assert(position.getX() == 47);
	assert(position.getY() == 11);
	mordor[0] = 'X'; // Cannot assign to readonly type const char
	assert(position.getName() == "Mordor");
}

void lsv3::positionCompareTest() {
	const std::string mordor = "Mordor";
	const lsv3::Position position(mordor, 47, 11);
	assert(position.compare(position) == 0);
	const lsv3::Position position_name("zordor", 47, 11);
	const lsv3::Position position_x(mordor, 48, 11);
	const lsv3::Position position_y(mordor, 47, 12);
	assert(position.compare(position_name) < 0);
	assert(position.compare(position_x) < 0);
	assert(position.compare(position_y) < 0);
	assert(position_name.compare(position) >0 );
	assert(position_x.compare(position) > 0);
	assert(position_y.compare(position) > 0);
}

void lsv3::sort(lsv3::Position *positions, const int size) {
	for (int i = 1; i < size; ++i) {
		lsv3::Position key = positions[i];
		int j = i - 1;
		while (j >= 0 && positions[j].compare(key) > 0) {
			positions[j + 1] = positions[j];
			j = j - 1;
		}
		positions[j + 1] = key;
	}
}
