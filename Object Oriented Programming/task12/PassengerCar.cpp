#include "PassengerCar.h"
#include <iostream>

namespace lsv12 {
	PassengerCar::PassengerCar(const std::string &numberPlate, const std::string &manufacturer, const bool sunRoof): Vehicle(numberPlate, manufacturer), sunRoof(sunRoof) {}

	void PassengerCar::dump() {
		Vehicle::dump();
		std::cout << "  " << this->sunRoof;
	}

	bool PassengerCar::hasSunRoof() const {
		return this->sunRoof;
	}
}
