#ifndef CITY_H_10
#define CITY_H_10
#include <string>
#include <memory>
#include "../task7/Position.h"
#include <iostream>
#include <vector>

namespace lsv10 {
	typedef std::vector<std::string> strvec;
	typedef unsigned int uint;

	class City {
	private:
		lsv7::Position position;
		strvec pois;
		City(const lsv7::Position &position, const strvec &pois);
		static strvec createStringVector(const std::string *array, int size);
		void checkIndex(int index) const;
		[[nodiscard]] const std::string &getPOI(int index) const;
		void setPOI(int index, const std::string &poi);

	public:
		City(const std::string &name, int x, int y);
		City(const std::string &name, int x, int y, const std::string *pois, int numberOfPOIs);

		[[nodiscard]] const std::string &getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] uint getNumberOfPOIs() const;

		void add(const std::string &name);
		bool remove(const std::string &name);

		std::string &operator[](int index);
		const std::string &operator[](int index) const;
		friend std::ostream &operator<<(std::ostream &os, const City &city);

		// No longer needed, but explicit is better than implicit
		City(const lsv10::City &other) = default;
		City &operator=(const City &other) = default;
		~City() = default;
	};
}

#endif
