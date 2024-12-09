#ifndef TASK10_H
#define TASK10_H
#include <cassert>
#include <locale>

#include "City.h"

namespace lsv10 {
	void runTask10();

	lsv10::City createCity();
	void testEmpty();
	void testAdd();
	void testRemove();
	void cityIndexTest();
	void cityCopyIndexTest();
}

#endif
