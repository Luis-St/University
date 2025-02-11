#ifndef TASK11_H
#define TASK11_H
#include "../task7/task7.h"
#include <vector>

namespace lsv11 {
	void runTask11();

	void sort(std::vector<lsv7::Position> &positions);
	void test_sort(std::vector<lsv7::Position> &positions);
	void test_sort();

	void test_character();
}

#endif
