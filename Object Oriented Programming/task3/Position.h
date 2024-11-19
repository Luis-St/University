#ifndef POSITION_H_3
#define POSITION_H_3
#include <string>

namespace lsv3 {
	class Position {
	private:
		std::string name;
		int x;
		int y;

	public:
		Position(const std::string &name, int x, int y);

		[[nodiscard]] const std::string& getName() const;
		[[nodiscard]] int getX() const;
		[[nodiscard]] int getY() const;

		[[nodiscard]] int compare(const lsv3::Position &other) const;
	};
}

#endif
