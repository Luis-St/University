#ifndef POINT_H
#define POINT_H
#include <ostream>

class Point {
public:
	[[nodiscard]] virtual int getX() const = 0;
	[[nodiscard]] virtual int getY() const = 0;
	[[nodiscard]] virtual int x() = 0;
	[[nodiscard]] virtual int y() = 0;
	virtual ~Point() = default;

	bool operator<(const Point &other) const {
		return this->getX() < other.getX() && this->getY() < other.getY();
	}

	bool operator==(const Point &other) const {
		return this->getX() == other.getX() && this->getY() == other.getY();
	}
};

#endif
