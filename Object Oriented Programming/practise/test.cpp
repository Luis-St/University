#include <iostream>
#include "test.h"

#include <array>
#include <map>
#include <memory>
#include <set>
#include <vector>

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
	runAssignTest();
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
	for (int i = 0; i < 10; i++) {
		array[i] = 10;
	}
	std::cout << "Array: ";
	for (int i = 0; i < 10; i++) {
		std::cout << array[i];
		if (i != 9) {
			std::cout << ", ";
		}
	}
	std::cout << std::endl;

	for (int i = 0; i < 10; i++) {
		*(array + i) = 42;
	}
	std::cout << "Array: ";
	for (int i = 0; i < 10; i++) {
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
	auto unique_point = std::make_unique<ArrayPoint>(10, 10);
	std::cout << "(" << unique_point->getX() << ", " << unique_point->getY() << ")" << std::endl;
	auto moved_unique_point = std::move(unique_point);
	std::cout << unique_point << std::endl;

	const auto shared_point = std::make_shared<ArrayPoint>(10, 10);
	std::cout << shared_point.use_count() << std::endl;
	auto shared_point_two = shared_point;
	std::cout << shared_point.use_count() << std::endl;
	shared_point_two = nullptr;
	std::cout << shared_point.use_count() << std::endl;
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
	std::array<int, 5> arr = {1, 2, 3, 4, 5};
	std::cout << "Array before: ";
	for (const int i: arr) {
		std::cout << i << " ";
	}
	std::cout << std::endl;
	arr[0] = 10;
	std::cout << "Array after: ";
	for (const int i: arr) {
		std::cout << i << " ";
	}
	std::cout << std::endl;

	std::vector<int> vec = {1, 2, 3, 4, 5};
	std::cout << "Vector before: ";
	for (const int i: vec) {
		std::cout << i << " ";
	}
	std::cout << std::endl;
	vec.push_back(6);
	vec.erase(vec.begin());
	std::cout << "Vector after: ";
	for (const int i: vec) {
		std::cout << i << " ";
	}
	std::cout << std::endl;

	std::set<int> s = {1, 2, 3, 4, 5};
	std::cout << "Set before: ";
	for (const int i: s) {
		std::cout << i << " ";
	}
	std::cout << std::endl;
	s.insert(6);
	s.erase(s.begin());
	std::cout << "Set after: ";
	for (const int i: s) {
		std::cout << i << " ";
	}
	std::cout << std::endl;

	std::map<int, std::string> m = {{1, "one"}, {2, "two"}, {3, "three"}};
	std::cout << "Map before: ";
	for (const auto &[key, value]: m) {
		std::cout << "{" << key << ", " << value << "} ";
	}

	std::cout << std::endl;
	m[4] = "four";
	m.erase(m.begin());
	std::cout << "Map after: ";
	for (const auto &[key, value]: m) {
		std::cout << "{" << key << ", " << value << "} ";
	}
	std::cout << std::endl;
}

class Base {
public:
	virtual void dump() { std::cout << "in Base " << std::endl; }
};

class Sub : public Base {
public:
	void dump() override { std::cout << "in Sub " << std::endl; }
};

void runAssignTest() {
	std::cout << std::endl << "--- Assign test ---" << std::endl;
	Base baseVariable = Sub();
	baseVariable.dump();
	// Output: in Base

	Sub sub;
	Base &baseReference = sub;
	baseReference.dump();
	// Output: in Sub

	Base *basePointer = new Sub();
	basePointer->dump();
	delete basePointer;
	// Output: in Sub
}
