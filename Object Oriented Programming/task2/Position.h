#ifndef POSITION_H
#define POSITION_H
#include <string>

namespace ls {
	class Position {
	private:
		std::string name;
		int x = 0;
		int y = 0;

	public:
		void set(const std::string &name, const int x, const int y) {
			this->name = name;
			this->x = x;
			this->y = y;
		}

		[[nodiscard]] const std::string &getName() const {
			return this->name;
		}

		[[nodiscard]] int getX() const {
			return this->x;
		}

		[[nodiscard]] int getY() const {
			return this->y;
		}
	};
}

#endif
