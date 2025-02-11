#include "Hexagon.h"

#include <cmath>
#include <iostream>

const double Hexagon::AREA_CONSTANT = (3 * std::sqrt(3) / 2);

Hexagon::Hexagon(const double length): Square(length), Circle(length), length(length) {
	std::cout << "Hexagon ctor" << std::endl;
}

double Hexagon::getPerimeter() const {
	return this->length * 6;
}

double Hexagon::getArea() const {
	return AREA_CONSTANT * this->length * this->length;
}

Hexagon::~Hexagon() {
	std::cout << "Hexagon dtor" << std::endl;
}
