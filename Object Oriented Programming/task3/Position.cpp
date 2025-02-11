#include "Position.h"

namespace lsv3 {
	Position::Position(const std::string &name, const int x, const int y) : name(name), x(x), y(y) {}

	/*Position::Position(const std::string &name, const int x, const int y) {
		this->name = name;
		this->x = x;
		this->y = y;
	}*/

	const std::string &Position::getName() const {
		return this->name;
	}

	int Position::getX() const {
		return this->x;
	}

	int Position::getY() const {
		return this->y;
	}

	int Position::compare(const Position &other) const {
		if (this->name != other.name) {
			return this->name < other.name ? -1 : 1;
		}
		if (this->x != other.x) {
			return this->x < other.x ? -1 : 1;
		}
		if (this->y != other.y) {
			return this->y < other.y ? -1 : 1;
		}
		return 0;
	}
}
