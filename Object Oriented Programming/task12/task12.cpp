#include "task12.h"

#include <iostream>

#include "StationWagon.h"
#include "Van.h"

void lsv12::runTask12() {
	std::cout << std::boolalpha;
	lsv12::Van van("VS XY 42", "BMW", 100);
	van.dump();
	std::cout << std::endl;
	Vehicle &vehicle = van;
	vehicle.dump();
	std::cout << std::endl;
	lsv12::StationWagon stationWagon("VS XY 42", "BMW", 100, true, 8);
	stationWagon.dump();
	std::cout << std::endl;
}
