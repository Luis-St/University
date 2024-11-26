#include "task8.h"
#include <cassert>

#include "City.h"

void lsv8::runTask8() {
	lsv8::cityIndexTest();
	lsv8::cityCopyIndexTest();
}

lsv8::City createCity() {
	const std::string mordor = "Mordor";
	const std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv8::City result(mordor, 47, 11, pois, 3);
	return result;
}

void lsv8::cityIndexTest() {
	auto city = createCity();
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

void lsv8::cityCopyIndexTest() {
	std::string pois_morder[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv8::City left("Mordor", 47, 11, pois_morder, 3);
	std::string pois_arrakis[] = {"Arrakeen", "Desert"};
	lsv8::City right("Arrakis", 23, 42, pois_arrakis, 2);
	left = right;
	assert(left.getName() == "Arrakis");
	assert(left.getX() == 23);
	assert(left.getY() == 42);
	assert(left.getNumberOfPOIs() == 2);
	assert(left[0] == pois_arrakis[0]);
	assert(left[1] == pois_arrakis[1]);
}
