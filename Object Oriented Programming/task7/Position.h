#ifndef POSITION_H_7
#define POSITION_H_7
#include <string>
#include <iostream>

namespace lsv7 {
	class Position {
	private:
		std::string name;
		int x;
		int y;
		[[nodiscard]] int compare(const lsv7::Position &other) const;

	public:
		Position(const std::string &name, int x, int y);

		[[nodiscard]] const std::string &getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;

		bool operator==(const lsv7::Position &other) const;
		bool operator!=(const lsv7::Position &other) const;
		bool operator<(const lsv7::Position &other) const;
		bool operator<=(const lsv7::Position &other) const;
		bool operator>(const lsv7::Position &other) const;
		bool operator>=(const lsv7::Position &other) const;

		friend std::ostream &operator<<(std::ostream &os, const Position &position);
	};
}

#endif
