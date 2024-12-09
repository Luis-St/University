#include "task10.h"

#include <iostream>

#include "City.h"

void lsv10::runTask10() {
	lsv10::testEmpty();
	lsv10::testAdd();
	lsv10::testRemove();
	lsv10::cityIndexTest();
	lsv10::cityCopyIndexTest();
}

void lsv10::testEmpty() {
	lsv10::City city("Mordor", 47, 11);
	assert(city.getNumberOfPOIs() == 0);
	try {
		lsv10::City city("Mordor", 47, 11, nullptr, 23);
		assert(false);
	} catch (...) {}
	const lsv10::City empty("Mordor", 47, 11);
	assert(empty.getNumberOfPOIs() == 0);
}

void lsv10::testAdd() {
	std::string pois[] = {"Sauron", "Minas Morgul"};
	lsv10::City city("Mordor", 47, 11, pois, 2);
	city.add("Orodruin");
	assert(city.getNumberOfPOIs() == 3);
	assert(city[2] == "Orodruin");
	lsv10::City empty("Mordor", 47, 11);
	empty.add("Orodruin");
	assert(empty.getNumberOfPOIs() == 1);
	assert(empty[0] == "Orodruin");
}

void lsv10::testRemove() {
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	const lsv10::City city("Mordor", 47, 11, pois, 3);
	lsv10::City copy1 = city;
	assert(true == copy1.remove("Orodruin"));
	assert(copy1.getNumberOfPOIs() == 2);
	assert(false == copy1.remove("Shire"));
	lsv10::City copy2 = city;
	assert(true == copy2.remove("Sauron"));
	assert(copy2.getNumberOfPOIs() == 2);
	assert(copy2[0] == "Minas Morgul");
	std::string saurons[] = {"Sauron", "Sauron", "Sauron"};
	lsv10::City same("Mordor", 47, 11, saurons, 3);
	assert(true == same.remove("Sauron"));
	assert(same.getNumberOfPOIs() == 0);
}

lsv10::City lsv10::createCity() {
	const std::string mordor = "Mordor";
	const std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv10::City result(mordor, 47, 11, pois, 3);
	return result;
}

void lsv10::cityIndexTest() {
	auto city = lsv10::createCity();
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

void lsv10::cityCopyIndexTest() {
	std::string pois_morder[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv10::City left("Mordor", 47, 11, pois_morder, 3);
	std::string pois_arrakis[] = {"Arrakeen", "Desert"};
	lsv10::City right("Arrakis", 23, 42, pois_arrakis, 2);
	left = right;
	assert(left.getName() == "Arrakis");
	assert(left.getX() == 23);
	assert(left.getY() == 42);
	assert(left.getNumberOfPOIs() == 2);
	assert(left[0] == pois_arrakis[0]);
	assert(left[1] == pois_arrakis[1]);
}
