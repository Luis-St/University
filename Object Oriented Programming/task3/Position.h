#ifndef POSITION_H
#define POSITION_H
#include <string>

namespace lsv3 {
	class Position {
	private:
		std::string name;
		int x;
		int y;

	public:
		Position(const std::string &name, int x, int y);

		[[nodiscard]] std::string getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;

		[[nodiscard]] int compare(const lsv3::Position &other) const;
	};
}

#endif
