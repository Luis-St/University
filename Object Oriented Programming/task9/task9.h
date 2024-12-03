#ifndef TASK9_H
#define TASK9_H
#include <cassert>
#include <locale>

#include "City.h"

namespace lsv9 {
	void runTask9();

	bool compare_chars(char first, char second);
	void test_sort_ints();
	void test_sort_chars();

	lsv9::City createCity();
	void cityIndexTest();
	void cityCopyIndexTest();

	template<typename T>
	void sort(T *array, const int size) {
		for (int i = 1; i < size; ++i) {
			T key = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}

	template<>
	inline void sort(char *array, const int size) {
		for (int i = 1; i < size; ++i) {
			const char key = array[i];
			int j = i - 1;
			while (j >= 0 && lsv9::compare_chars(array[j], key)) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}

	template<typename T>
	void test_sort(T *array, const int size) {
		sort(array, size);
		for (int i = 0; i < size - 1; ++i) {
			assert(array[i] <= array[i + 1]);
		}
	}
}

#endif
