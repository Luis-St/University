#include "Van.h"
#include <iostream>

namespace lsv12 {
	Van::Van(const std::string &numberPlate, const std::string &manufacturer, const int maxLoad): Vehicle(numberPlate, manufacturer), maxLoad(maxLoad) {}

	void Van::dump() {
		this->Vehicle::dump();
		std::cout << "  " << this->maxLoad;
	}

	int Van::getMaxLoad() const {
		return this->maxLoad;
	}
}
