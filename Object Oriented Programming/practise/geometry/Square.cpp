#include "Square.h"

#include <iostream>

Square::Square(const double length): Rectangle(length, length), length(length) {
	std::cout << "Square ctor" << std::endl;
}

double Square::getPerimeter() const {
	return this->length * 4;
}

double Square::getArea() const {
	return this->length * this->length;
}

Square::~Square() {
	std::cout << "Square dtor" << std::endl;
}
