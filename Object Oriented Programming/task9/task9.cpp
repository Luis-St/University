#include "task9.h"

#include <iostream>

#include "City.h"

void lsv9::runTask9() {
	lsv9::test_sort_ints();
	lsv9::test_sort_chars();
	lsv9::cityIndexTest();
	lsv9::cityCopyIndexTest();
}

bool lsv9::compare_chars(const char first, const char second) {
	if (std::tolower(first) == std::tolower(second)) {
		return first < second; // If the characters are the same, return the one that is lowercase
	} else {
		return std::tolower(first) > std::tolower(second);
	}
}

void lsv9::test_sort_ints() {
	int numbers0[] = {1, 2, 3, 4, 5};
	lsv9::test_sort(numbers0, 5);

	int numbers1[] = {5, 4, 3, 2, 1};
	lsv9::test_sort(numbers1, 5);

	int numbers2[] = {2, 6, 3, 1, 5, 4, 7};
	lsv9::test_sort(numbers2, 7);

	int numbers3[] = {1, 1, 1, 1, 1, 1, 1};
	lsv9::test_sort(numbers3, 7);
}

void lsv9::test_sort_chars() {
	char chars[] {'B', 'a', 'A', 'x'};
	constexpr char expected[] {'a', 'A', 'B', 'x'};
	sort(chars, 4);
	for (int i = 0; i < 4; ++i) {
		assert(chars[i] == expected[i]);
	}
}

lsv9::City lsv9::createCity() {
	const std::string mordor = "Mordor";
	const std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv9::City result(mordor, 47, 11, pois, 3);
	return result;
}

void lsv9::cityIndexTest() {
	auto city = lsv9::createCity();
	assert(city[0] == "Sauron");
	assert(city[1] == "Minas Morgul");
	assert(city[2] == "Orodruin");
	try {
		city[3];
		assert(false);
	} catch (...) {}
	try {
		city[-1];
		assert(false);
	} catch (...) {}
	city[0] = "Gandalf";
	assert(city[0] == "Gandalf");
	try {
		city[3] = "Gandalf";
		assert(false);
	} catch (...) {}
	try {
		city[-1] = "Gandalf";
		assert(false);
	} catch (...) {}
	const auto constCity = createCity();
	assert(constCity[0] == "Sauron");
}

void lsv9::cityCopyIndexTest() {
	std::string pois_morder[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv9::City left("Mordor", 47, 11, pois_morder, 3);
	std::string pois_arrakis[] = {"Arrakeen", "Desert"};
	lsv9::City right("Arrakis", 23, 42, pois_arrakis, 2);
	left = right;
	assert(left.getName() == "Arrakis");
	assert(left.getX() == 23);
	assert(left.getY() == 42);
	assert(left.getNumberOfPOIs() == 2);
	assert(left[0] == pois_arrakis[0]);
	assert(left[1] == pois_arrakis[1]);
}