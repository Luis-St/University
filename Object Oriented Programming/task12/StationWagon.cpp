#include "StationWagon.h"
#include <iostream>

namespace lsv12 {
	StationWagon::StationWagon(const std::string &numberPlate, const std::string &manufacturer, const int maxLoad, const bool sunRoof, const int numSeats): Van(numberPlate, manufacturer, maxLoad),
																																							PassengerCar(numberPlate, manufacturer, sunRoof),
																																							numSeats(numSeats) {}

	void StationWagon::dump() {
		Van::dump();
		//PassengerCar::dump(); // Will also print the numberPlate and manufacturer again
		std::cout << "  " << this->getMaxLoad() << "  " << this->hasSunRoof() << "  " << this->numSeats;
	}

	int StationWagon::getNumSeats() const {
		return this->numSeats;
	}
}
