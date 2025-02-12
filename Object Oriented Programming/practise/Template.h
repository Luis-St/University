#ifndef TEMPLATE_H
#define TEMPLATE_H
#include <locale>

class Template {
public:
	template<typename T>
	bool compare(const T first, const T second) {
		return first > second;
	}

	template<>
	bool compare(const char first, const char second) {
		return std::tolower(first) > std::tolower(second);
	}

	template<>
	bool compare(const int first, const int second) = delete;
};

#endif
