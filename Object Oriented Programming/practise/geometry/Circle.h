#ifndef CIRCLE_H
#define CIRCLE_H
#include "Figure.h"

class Circle : public Figure {
private:
	double diameter;
	static const double PI;

public:
	explicit Circle(double diameter);
	[[nodiscard]] double getPerimeter() const override;
	[[nodiscard]] double getArea() const override;
	~Circle() override;
};

#endif
