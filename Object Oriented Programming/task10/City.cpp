#include "City.h"
#include <iostream>
#include <ostream>
#include <stdexcept>
#include <algorithm>

namespace lsv10 {
	City::City(const lsv7::Position &position, const strvec& pois): position(position), pois(pois) {}

	strvec City::createStringVector(const std::string *array, const int size) {
		if (array == nullptr && size != 0) {
			throw std::invalid_argument("Array is null but size is not 0");
		}
		auto result = strvec(size);
		for (int i = 0; i < size; i++) {
			result[i] = array[i];
		}
		return result;
	}

	void City::checkIndex(const int index) const {
		if (index < 0 || index >= this->pois.size()) {
			throw std::out_of_range("Index out of range");
		}
	}

	const std::string &City::getPOI(const int index) const {
		checkIndex(index);
		return this->pois[index];
	}

	void City::setPOI(const int index, const std::string &poi) {
		checkIndex(index);
        this->pois.at(index) = poi;
	}

	City::City(const std::string &name, const int x, const int y): City(lsv7::Position(name, x, y), strvec()) {}

	City::City(const std::string &name, const int x, const int y, const std::string *pois, const int numberOfPOIs): City(lsv7::Position(name, x, y), createStringVector(pois, numberOfPOIs)) {}

	City::City(const lsv10::City &other): City(other.position, other.pois) {}

	const std::string &City::getName() const {
		return this->position.getName();
	}

	int City::getX() const {
		return this->position.getX();
	}

	int City::getY() const {
		return this->position.getY();
	}

	uint City::getNumberOfPOIs() const {
		return this->pois.size();
	}

	void City::add(const std::string &name) {
		this->pois.push_back(name);
	}

	bool City::remove(const std::string &name) {
		const uint size = this->pois.size();
		std::erase(this->pois, name);
		return size != this->pois.size();
	}

	City& City::operator=(const City &other) {
		if (this != &other) {
			this->position = other.position;
			this->pois = other.pois;
		}
		return *this;
	}

	std::string &City::operator[](const int index) {
		checkIndex(index);
		return this->pois[index];
	}

	const std::string &City::operator[](const int index) const {
		return this->getPOI(index);
	}

	std::ostream& operator<<(std::ostream& os, const City& city) {
		os << city.position << std::endl;
		for (int i = 0; i < city.getNumberOfPOIs(); ++i) {
			os << city.pois[i] << std::endl;
		}
		return os;
	}
}
