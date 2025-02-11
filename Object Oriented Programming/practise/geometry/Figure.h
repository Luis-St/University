#ifndef FIGURE_H
#define FIGURE_H
#include <iostream>

class Figure {
public:
	[[nodiscard]] virtual double getPerimeter() const = 0; // Umfang
	[[nodiscard]] virtual double getArea() const = 0; // Fläche
	virtual ~Figure() = default;
};

#endif
