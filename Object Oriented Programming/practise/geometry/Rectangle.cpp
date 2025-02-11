#include "Rectangle.h"

#include <iostream>

Rectangle::Rectangle(const double x, const double y): x(x), y(y) {
	std::cout << "Rectangle ctor" << std::endl;
}

double Rectangle::getPerimeter() const {
	return (this->x * 2) + (this->y * 2);
}

double Rectangle::getArea() const {
	return this->x * this->y;
}

Rectangle::~Rectangle() {
	std::cout << "Rectangle dtor" << std::endl;
}
