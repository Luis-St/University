#include "task11.h"
#include <cassert>
#include <map>
#include <ranges>
#include "Character.h"

void lsv11::runTask11() {
	lsv11::test_sort();
	lsv11::test_character();
}

void lsv11::sort(std::vector<lsv7::Position> &positions) {
	std::map<lsv7::Position, int> map;

	for (const auto &position: positions) {
		map[position] = 0;
	}

	positions.clear();
	for (const auto &key: map | std::views::keys) {
		positions.push_back(key);
	}
}

void lsv11::test_sort(std::vector<lsv7::Position> &positions) {
	sort(positions);
	for (int i = 1; i < positions.size(); ++i) {
		assert(positions[i - 1] <= positions[i]);
	}
}

void lsv11::test_sort() {
	std::vector<lsv7::Position> positions;
	test_sort(positions);
	positions.push_back(lsv7::Position("Test1", 1, 1));
	test_sort(positions);
	positions.push_back(lsv7::Position("Test2", 2, 2));
	test_sort(positions);
	positions.push_back(lsv7::Position("Test3", 3, 3));
	test_sort(positions);
}

void lsv11::test_character() {
	lsv11::Dwarf dwarf("Dwarf", 20, 2);
	lsv11::Elf elf("Elf", 10, 100);
	lsv11::Orc orc("Orc", 50, 5);

	// Dwarf base
	assert(dwarf.getName() == "Dwarf");
	assert(dwarf.getAxeWeight() == 2);
	assert(dwarf.isAlife());

	// Elf base
	assert(elf.getName() == "Elf");
	assert(elf.getArrowCount() == 100);
	assert(elf.isAlife());

	// Orc base
	assert(orc.getName() == "Orc");
	assert(orc.getSwordLength() == 5);
	assert(orc.isAlife());

	// Dwarf target
	elf.applyWeapon(dwarf);
	assert(dwarf.getHealth() == 18);
	orc.applyWeapon(dwarf);
	assert(dwarf.getHealth() == 17);

	// Elf target
	dwarf.applyWeapon(elf);
	assert(elf.getHealth() == 7);
	orc.applyWeapon(elf);
	assert(elf.getHealth() == 6);

	// Orc target
	dwarf.applyWeapon(orc);
	assert(orc.getHealth() == 47);
	elf.applyWeapon(orc);
	assert(orc.getHealth() == 45);

	// Post attack tests
	assert(elf.getArrowCount() == 98);
}
