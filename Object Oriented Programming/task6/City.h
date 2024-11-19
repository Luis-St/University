#ifndef CITY_H_6
#define CITY_H_6
#include "../task3/Position.h"

namespace lsv6 {
	class City {
	private:
		lsv3::Position position;
		std::string* pois;
		int numberOfPOIs;
		City(const lsv3::Position& position, const std::string *pois, int numberOfPOIs);
		void checkIndex(int index) const;
		static std::string* copyStringArray(const std::string *array, int size);
	public:
		City(const std::string& name, int x, int y);
		City(const std::string& name, int x, int y, const std::string *pois, int numberOfPOIs);
		City(const lsv6::City& other);
		[[nodiscard]] const std::string& getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;
		[[nodiscard]] const std::string& getPOI(int index) const;
		void setPOI(int index, const std::string& poi) const;
		[[nodiscard]] int getNumberOfPOIs() const;
		void add(const std::string& name);
		bool remove(const std::string& name);
		~City();
	};
}

#endif
