#include "City.h"

#include <stdexcept>

namespace lsv4 {

	void City::checkIndex(const int index) const {
		if (index < 0 || index >= this->numberOfPOIs) {
			throw std::out_of_range("Index out of range");
		}
	}

	std::string* City::createPOIs(const std::string pois[], const int numberOfPOIs) {
		const auto result = new std::string[numberOfPOIs];
		for (int i = 0; i < numberOfPOIs; i++) {
			result[i] = pois[i];
		}
		return result;
	}

	City::City(const lsv3::Position& position, const std::string pois[], const int numberOfPOIs): position(position), pois(createPOIs(pois, numberOfPOIs)), numberOfPOIs(numberOfPOIs) {}

	std::string City::getName() const {
		return this->position.getName();
	}

	int City::getX() const {
		return this->position.getX();
	}

	int City::getY() const {
		return this->position.getY();
	}

	std::string City::getPOI(const int index) const {
		checkIndex(index);
		return this->pois[index];
	}

	void City::setPOI(const int index, const std::string& poi) const {
		checkIndex(index);
		this->pois[index] = poi;
	}

	int City::getNumberOfPOIs() const {
		return this->numberOfPOIs;
	}
}
