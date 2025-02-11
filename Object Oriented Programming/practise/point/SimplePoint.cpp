#include "SimplePoint.h"

SimplePoint::SimplePoint(const int x, const int y): x_value(x), y_value(y) {}

int SimplePoint::getX() const {
	return this->x_value;
}

int SimplePoint::getY() const {
	return this->y_value;
}

int SimplePoint::x() {
	return this->x_value;
}

int SimplePoint::y() {
	return this->y_value;
}

std::ostream &operator<<(std::ostream &os, const SimplePoint &point) {
	os << "(" << point.x_value << ", " << point.y_value << ")" << std::endl;
	return os;
}
