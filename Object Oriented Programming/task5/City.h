#ifndef CITY_H
#define CITY_H
#include "../task3/Position.h"

namespace lsv5 {
	class City {
	private:
		lsv3::Position position;
		std::string* pois;
		int numberOfPOIs;
		City(const lsv3::Position& position, const std::string *pois, int numberOfPOIs);
		void checkIndex(int index) const;
		static std::string* createPOIs(const std::string *pois, int numberOfPOIs);
	public:
		City(const std::string& name, int x, int y, const std::string *pois, int numberOfPOIs);
		City(const lsv5::City& other);
		[[nodiscard]] const std::string& getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] const std::string& getPOI(int index) const;
		void setPOI(int index, const std::string& poi) const;
		[[nodiscard]] int getNumberOfPOIs() const;
		~City();
	};
}

#endif
