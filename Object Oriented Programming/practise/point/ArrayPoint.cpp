#include "Point.h"
#include "ArrayPoint.h"

int *ArrayPoint::createArray(const int x, const int y) {
	int *array = new int[2];
	array[0] = x;
	array[1] = y;
	return array;
}

ArrayPoint::ArrayPoint(const int x, const int y): points(createArray(x, y)) {}

ArrayPoint::ArrayPoint(const ArrayPoint &other): points(createArray(other.points[0], other.points[1])) {}

int ArrayPoint::getX() const {
	return this->points[0];
}

int ArrayPoint::getY() const {
	return this->points[1];
}

int ArrayPoint::x() {
	return this->points[0];
}

int ArrayPoint::y() {
	return this->points[1];
}

ArrayPoint::~ArrayPoint() {
	delete[] this->points;
}

ArrayPoint &ArrayPoint::operator=(const ArrayPoint &other) {
	if (this == &other) {
		return *this;
	}
	this->points[0] = other.getX();
	this->points[1] = other.getY();
	return *this;
}

std::ostream &operator<<(std::ostream &os, const ArrayPoint &point) {
	os << "(" << point.points[0] << ", " << point.points[1] << ")" << std::endl;
	return os;
}
