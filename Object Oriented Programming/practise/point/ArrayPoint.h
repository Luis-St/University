#ifndef ARRAYPOINT_H
#define ARRAYPOINT_H
#include "Point.h"

class ArrayPoint final : public Point {
private:
	int *points;
	static int *createArray(int x, int y);

public:
	ArrayPoint(int x, int y);
	ArrayPoint(const ArrayPoint &other);
	[[nodiscard]] int getX() const override;
	[[nodiscard]] int getY() const override;
	[[nodiscard]] int x() override;
	[[nodiscard]] int y() override;
	~ArrayPoint() override;
	ArrayPoint &operator=(const ArrayPoint &other);
	friend std::ostream &operator<<(std::ostream &os, const ArrayPoint &point);
};

#endif
