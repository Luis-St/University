#ifndef CITY_H_7
#define CITY_H_7
#include <string>
#include "Position.h"
#include <iostream>

namespace lsv7 {
	class City {
	private:
		lsv7::Position position;
		std::string* pois;
		int numberOfPOIs;
		City(const lsv7::Position& position, const std::string *pois, int numberOfPOIs);
		void checkIndex(int index) const;
		static std::string* copyStringArray(const std::string *array, int size);
	public:
		City(const std::string& name, int x, int y);
		City(const std::string& name, int x, int y, const std::string *pois, int numberOfPOIs);
		City(const lsv7::City& other);

		[[nodiscard]] const std::string& getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] const std::string& getPOI(int index) const;

		void setPOI(int index, const std::string& poi) const;
		[[nodiscard]] int getNumberOfPOIs() const;

		void add(const std::string& name);
		bool remove(const std::string& name);

		friend std::ostream& operator<<(std::ostream& os, const City& city);

		~City();
	};
}

#endif
