#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "Figure.h"

class Rectangle : public Figure {
private:
	double x;
	double y;

public:
	Rectangle(double x, double y);
	[[nodiscard]] double getPerimeter() const override;
	[[nodiscard]] double getArea() const override;
	~Rectangle() override;
};

#endif
