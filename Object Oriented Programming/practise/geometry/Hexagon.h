#ifndef HEXAGON_H
#define HEXAGON_H
#include "Circle.h"
#include "Square.h"

class Hexagon final : public Circle, public Square {
private:
	double length;
	static const double AREA_CONSTANT;

public:
	explicit Hexagon(double length);
	[[nodiscard]] double getPerimeter() const override;
	[[nodiscard]] double getArea() const override;
	~Hexagon() override;
};

#endif
