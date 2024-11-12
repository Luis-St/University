#include "task6.h"
#include <cassert>
#include "City.h"

void lsv6::runTask6() {
	lsv6::testEmpty();
	lsv6::testAdd();
	lsv6::testRemove();
}

void lsv6::testEmpty() {
	lsv6::City city("Mordor", 47, 11, nullptr, 0);
	assert(city.getNumberOfPOIs() == 0);
	try {
		lsv6::City city("Mordor", 47, 11, nullptr, 23);
		assert(false);
	} catch (...) {}
	const lsv6::City empty("Mordor", 47, 11);
	assert(empty.getNumberOfPOIs() == 0);
}

void lsv6::testAdd() {
	std::string pois[] = {"Sauron", "Minas Morgul"};
	lsv6::City city("Mordor", 47, 11, pois, 2);
	city.add("Orodruin");
	assert(city.getNumberOfPOIs() == 3);
	assert(city.getPOI(2)=="Orodruin");
	lsv6::City empty("Mordor", 47, 11);
	empty.add("Orodruin");
	assert(empty.getNumberOfPOIs() == 1);
	assert(empty.getPOI(0) == "Orodruin");
}

void lsv6::testRemove() {
	std::string pois[] = {"Sauron", "Minas Morgul", "Orodruin"};
	lsv6::City city("Mordor", 47, 11, pois, 3);
	lsv6::City copy1 = city;
	assert(true == copy1.remove("Orodruin"));
	assert(copy1.getNumberOfPOIs() == 2);
	assert(false == copy1.remove("Shire"));
	lsv6::City copy2 = city;
	assert(true == copy2.remove("Sauron"));
	assert(copy2.getNumberOfPOIs() == 2);
	assert(copy2.getPOI(0) == "Minas Morgul");
	std::string saurons[] = {"Sauron", "Sauron", "Sauron"};
	lsv6::City same("Mordor", 47, 11, saurons, 3);
	assert(true == same.remove("Sauron"));
	assert(same.getNumberOfPOIs() == 0);
}
