#ifndef CHARACTER_H
#define CHARACTER_H
#include <string>

namespace lsv11 {
	typedef unsigned int uint;

	class Character {
	private:
		std::string name;
		uint health = 0;

	public:
		Character(const std::string &name, const uint health): name(name), health(health) {}

		[[nodiscard]] const std::string &getName() const {
			return this->name;
		}

		[[nodiscard]] uint getHealth() const {
			return this->health;
		}

		[[nodiscard]] bool isAlife() const {
			return this->health > 0;
		}

		void hit() {
			if (this->isAlife()) {
				--this->health;
			}
		}

		virtual void applyWeapon(Character &target) = 0; // Assuming that the provided character is the target

		virtual ~Character() = default;
	};

	class Dwarf final : public Character {
	private:
		uint axeWeight;

	public:
		Dwarf(const std::string &name, const uint health, const uint axeWeight): Character(name, health), axeWeight(axeWeight) {}

		[[nodiscard]] uint getAxeWeight() const {
			return this->axeWeight;
		}

		void applyWeapon(Character &target) override {
			if (target.isAlife()) {
				target.hit();
				target.hit();
				target.hit();
			}
		}
	};

	class Elf final : public Character {
	private:
		uint arrowCount;

	public:
		Elf(const std::string &name, const uint health, const uint arrowCount): Character(name, health), arrowCount(arrowCount) {}

		[[nodiscard]] uint getArrowCount() const {
			return this->arrowCount;
		}

		void applyWeapon(Character &target) override {
			if (target.isAlife() && this->arrowCount > 0) {
				--this->arrowCount;
				target.hit();
				target.hit();
			}
		}
	};

	class Orc final : public Character {
	private:
		uint swordLength;

	public:
		Orc(const std::string &name, const uint health, const uint swordLength): Character(name, health), swordLength(swordLength) {}

		[[nodiscard]] uint getSwordLength() const {
			return this->swordLength;
		}

		void applyWeapon(Character &target) override {
			if (target.isAlife()) {
				target.hit();
			}
		}
	};
}

#endif
