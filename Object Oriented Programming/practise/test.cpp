#include <iostream>
#include "test.h"
#include "point/ArrayPoint.h"
#include "point/SimplePoint.h"

void runTests() {
	runPointTest();
}

void runPointTest() {
	const ArrayPoint array_point(9, 9);
	std::cout << array_point.getX() << array_point.getY() << std::endl;
	std::cout << array_point;

	const SimplePoint simple_point(10, 10);
	std::cout << simple_point;

	const SimplePoint p = simple_point;
	std::cout << p;

	if (array_point < simple_point) {
		std::cout << array_point << " is smaller than " << simple_point << std::endl;
	}
}
