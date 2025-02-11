#include "Circle.h"

#include <iostream>

const double Circle::PI = 3.14159265358979323846;

Circle::Circle(const double diameter): diameter(diameter) {
	std::cout << "Circle ctor" << std::endl;
}

double Circle::getPerimeter() const {
	return this->diameter * PI;
}

double Circle::getArea() const {
	const double radius = this->diameter / 2;
	return PI * (radius * radius);
}

Circle::~Circle() {
	std::cout << "Circle dtor" << std::endl;
}
