#include "task4.h"
#include <cassert>
#include <string>
#include "City.h"
#include "../task3/Position.h"


void lsv4::runTask4() {
	lsv4::testCtor();
	lsv4::testGetNameXY();
	lsv4::testGetPOI();
	lsv4::testSetPOI();
	lsv4::testGetNumberOfPOIs();
}

static lsv3::Position createPosition() {
	std::string mordor = "Mordor";
	lsv3::Position result(mordor, 47, 11);
	return result;
}

static lsv4::City createCity() {
	auto position = createPosition();
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv4::City result(position, pois, 3);
	return result;
}

void lsv4::testCtor() {
	auto position = createPosition();
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv4::City city(position, pois, 3);
	pois[0] = "Gandalf";
	assert(city.getPOI(0) == "Sauron");
}

void lsv4::testGetNameXY() {
	auto position = createPosition();
	lsv4::City city(position, nullptr, 0);
	assert(city.getName() == "Mordor");
	assert(city.getX() == 47);
	assert(city.getY() == 11);
}

void lsv4::testGetPOI() {
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

void lsv4::testSetPOI() {
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

void lsv4::testGetNumberOfPOIs() {
	auto city = createCity();
	assert(city.getNumberOfPOIs()==3);
}
