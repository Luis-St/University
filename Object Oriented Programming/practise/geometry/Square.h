#ifndef SQUARE_H
#define SQUARE_H
#include "Rectangle.h"

class Square : public Rectangle {
private:
	double length;

public:
	explicit Square(double length);
	[[nodiscard]] double getPerimeter() const override;
	[[nodiscard]] double getArea() const override;
	~Square() override;
};

#endif
