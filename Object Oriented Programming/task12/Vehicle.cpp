#include "Vehicle.h"
#include <iostream>

namespace lsv12 {
	Vehicle::Vehicle(const std::string &numberPlate, const std::string &manufacturer): numberPlate(numberPlate), manufacturer(manufacturer) {}

	void Vehicle::dump() {
		std::cout << numberPlate << "  " << manufacturer;
	}

	const std::string &Vehicle::getNumberPlate() const {
		return this->numberPlate;
	}

	const std::string &Vehicle::getManufacturer() const {
		return this->manufacturer;
	}
}
