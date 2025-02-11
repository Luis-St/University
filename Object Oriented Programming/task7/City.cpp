#include "City.h"

#include <iostream>
#include <ostream>
#include <stdexcept>

namespace lsv7 {
	City::City(const lsv7::Position &position, const std::string *pois, const int numberOfPOIs): position(position), pois(copyStringArray(pois, numberOfPOIs)), numberOfPOIs(numberOfPOIs) {}

	void City::checkIndex(const int index) const {
		if (index < 0 || index >= this->numberOfPOIs) {
			throw std::out_of_range("Index out of range");
		}
	}

	std::string *City::copyStringArray(const std::string *array, const int size) {
		if (array == nullptr && size != 0) {
			throw std::invalid_argument("Array is null but size is not 0");
		}
		const auto result = new std::string[size];
		for (int i = 0; i < size; i++) {
			result[i] = array[i];
		}
		return result;
	}

	City::City(const std::string &name, const int x, const int y): City(lsv7::Position(name, x, y), nullptr, 0) {}

	City::City(const std::string &name, const int x, const int y, const std::string *pois, const int numberOfPOIs): City(lsv7::Position(name, x, y), pois, numberOfPOIs) {}

	City::City(const lsv7::City &other): City(other.position, other.pois, other.numberOfPOIs) {}

	const std::string &City::getName() const {
		return this->position.getName();
	}

	int City::getX() const {
		return this->position.getX();
	}

	int City::getY() const {
		return this->position.getY();
	}

	const std::string &City::getPOI(const int index) const {
		checkIndex(index);
		return this->pois[index];
	}

	void City::setPOI(const int index, const std::string &poi) const {
		checkIndex(index);
		this->pois[index] = poi;
	}

	int City::getNumberOfPOIs() const {
		return this->numberOfPOIs;
	}

	void City::add(const std::string &name) {
		const auto result = copyStringArray(this->pois, this->numberOfPOIs + 1);
		result[this->numberOfPOIs] = name;
		this->numberOfPOIs = this->numberOfPOIs + 1;
		delete[] this->pois;
		this->pois = result;
	}

	bool City::remove(const std::string &name) {
		bool found = false;
		int newSize = 0;
		for (int i = 0; i < this->numberOfPOIs; ++i) {
			if (this->pois[i] != name) {
				this->pois[newSize++] = this->pois[i];
			} else {
				found = true;
			}
		}
		if (found) {
			this->numberOfPOIs = newSize;
			this->pois = copyStringArray(this->pois, this->numberOfPOIs);
		}
		return found;
	}

	std::ostream &operator<<(std::ostream &os, const City &city) {
		os << city.position << std::endl;
		for (int i = 0; i < city.numberOfPOIs; ++i) {
			os << city.pois[i] << std::endl;
		}
		return os;
	}

	City::~City() {
		delete[] this->pois;
		this->pois = nullptr;
	}
}
