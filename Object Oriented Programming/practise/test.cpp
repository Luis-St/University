#include <iostream>
#include "test.h"

#include "geometry/Circle.h"
#include "geometry/Hexagon.h"
#include "geometry/Rectangle.h"
#include "geometry/Square.h"
#include "point/ArrayPoint.h"
#include "point/SimplePoint.h"

void runTests() {
	runPointerTest();
	runPointTest();
	runSmartPointerTest();
	runGeometryTest();
	runCollectionTest();
}

void runPointerTest() {
	std::cout << "--- Pointer test ---" << std::endl;
	int *num = new int;
	std::cout << "Address: " << num << std::endl;
	*num = 42;
	std::cout << "Value: " << *num << std::endl;
	delete num;
	std::cout << "Value after delete: " << *num << std::endl;

	int *array = new int[10];
	for (int i = 0; i < 10; i ++) {
		array[i] = 10;
	}
	std::cout << "Array: ";
	for (int i = 0; i < 10; i ++) {
		std::cout << array[i];
		if (i != 9) {
			std::cout << ", ";
		}
	}
	std::cout << std::endl;


	for (int i = 0; i < 10; i ++) {
		*(array + i) = 42;
	}
	std::cout << "Array: ";
	for (int i = 0; i < 10; i ++) {
		std::cout << i[array];
		if (i != 9) {
			std::cout << ", ";
		}
	}
	std::cout << std::endl;

	delete[] array;
}

void runSmartPointerTest() {
	std::cout << "--- Smart pointer test ---" << std::endl;
}

void runPointTest() {
	std::cout << std::endl << "--- Point test ---" << std::endl;
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

void runGeometryTest() {
	std::cout << std::endl << "--- Geometry test ---" << std::endl;
	// Output: Rectangle ctor
	const Rectangle *rectangle = new Rectangle(5, 7);
	std::cout << "Rectangle: " << rectangle->getPerimeter() << ", " << rectangle->getArea() << std::endl;
	delete rectangle;
	std::cout << std::endl;
	// Output: Rectangle dtor

	// Output:
	//  - Rectangle ctor
	//  - Square ctor
	const Square *square = new Square(5);
	std::cout << "Square: " << square->getPerimeter() << ", " << square->getArea() << std::endl;
	delete square;
	std::cout << std::endl;
	// Output:
	//  - Square dtor
	//  - Rectangle dtor

	// Output: Circle ctor
	const Circle *circle = new Circle(5);
	std::cout << "Circle: " << circle->getPerimeter() << ", " << circle->getArea() << std::endl;
	delete circle;
	std::cout << std::endl;
	// Output: Circle dtor

	// Output:
	//  - Circle ctor
	//  - Rectangle ctor
	//  - Square ctor
	//  - Hexagon ctor
	const Hexagon *hexagon = new Hexagon(5);
	std::cout << "Hexagon: " << (*hexagon).getPerimeter() << ", " << (*hexagon).getArea() << std::endl;
	delete hexagon;
	// Output:
	//  - Hexagon dtor
	//  - Square dtor
	//  - Rectangle dtor
	//  - Circle dtor
}

void runCollectionTest() {
	std::cout << std::endl << "--- Collection test ---" << std::endl;
}
