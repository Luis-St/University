#include "task5.h"
#include <cassert>
#include <string>
#include "City.h"
#include "../task3/Position.h"

void lsv5::runTask5() {
	lsv5::testCtor();
	lsv5::testGetNameXY();
	lsv5::testGetPOI();
	lsv5::testSetPOI();
	lsv5::testGetNumberOfPOIs();
	lsv5::testExplicitForTask5();
}

static lsv5::City createCity() {
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv5::City result("Mordor", 47, 11, pois, 3);
	return result;
}

void lsv5::testCtor() {
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv5::City city("Mordor", 47, 11, pois, 3);
	pois[0] = "Gandalf";
	assert(city.getPOI(0)=="Sauron");
}

void lsv5::testGetNameXY() {
	lsv5::City city("Mordor", 47, 11, nullptr, 0);
	assert(city.getName() == "Mordor");
	assert(city.getX() == 47);
	assert(city.getY() == 11);
}

void lsv5::testGetPOI() {
	auto city = createCity();
	assert(city.getPOI(0) == "Sauron");
	assert(city.getPOI(1) == "Minas Morgul");
	assert(city.getPOI(2) == "Orodruin");
	try {
		city.getPOI(3);
		assert(false);
	} catch (...) {}
	try {
		city.getPOI(-1);
		assert(false);
	} catch (...) {}
}

void lsv5::testSetPOI() {
	auto city = createCity();
	city.setPOI(0, "Gandalf");
	assert(city.getPOI(0)=="Gandalf");

	try {
		city.setPOI(3, "Gandalf");
		assert(false);
	} catch (...) {}
	try {
		city.setPOI(-1, "Gandalf");
		assert(false);
	} catch (...) {}
}

void lsv5::testGetNumberOfPOIs() {
	auto city = createCity();
	assert(city.getNumberOfPOIs() == 3);
}

void lsv5::testExplicitForTask5() {
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv5::City mordor("Mordor", 47, 11, pois, 3);
	auto copy = mordor;
	mordor.setPOI(2, "Cirith Ungol");
	assert(copy.getPOI(2) == "Orodruin");
}
