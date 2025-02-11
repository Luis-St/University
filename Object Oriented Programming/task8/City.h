#ifndef CITY_H_8
#define CITY_H_8
#include <string>
#include "../task7/Position.h"
#include <iostream>

namespace lsv8 {
	class City {
	private:
		lsv7::Position position;
		std::string *pois;
		int numberOfPOIs;
		City(const lsv7::Position &position, const std::string *pois, int numberOfPOIs);
		static std::string *copyStringArray(const std::string *array, int size);
		void checkIndex(int index) const;
		[[nodiscard]] const std::string &getPOI(int index) const;
		void setPOI(int index, const std::string &poi) const;

	public:
		City(const std::string &name, int x, int y);
		City(const std::string &name, int x, int y, const std::string *pois, int numberOfPOIs);
		City(const lsv8::City &other);

		[[nodiscard]] const std::string &getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] int getNumberOfPOIs() const;

		void add(const std::string &name);
		bool remove(const std::string &name);

		City &operator=(const City &other);
		std::string &operator[](int index);
		const std::string &operator[](int index) const;
		friend std::ostream &operator<<(std::ostream &os, const City &city);

		~City();
	};
}

#endif
