#ifndef CITY_H
#define CITY_H
#include "../task3/task3.h"

namespace lsv4 {
	class City {
	private:
		lsv3::Position position;
		std::string* pois;
		int numberOfPOIs;
		void checkIndex(int index) const;
		static std::string* createPOIs(const std::string pois[], int numberOfPOIs);
	public:
		City(const lsv3::Position& position, const std::string pois[], int numberOfPOIs);
		[[nodiscard]] std::string getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] std::string getPOI(int index) const;
		void setPOI(int index, const std::string& poi) const;
		[[nodiscard]] int getNumberOfPOIs() const;
	};
}

#endif
