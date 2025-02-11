#ifndef SIMPLEPOINT_H
#define SIMPLEPOINT_H
#include "Point.h"

class SimplePoint final : public Point {
private:
	const int x_value;
	const int y_value;

public:
	SimplePoint(int x, int y);
	[[nodiscard]] int getX() const override;
	[[nodiscard]] int getY() const override;
	[[nodiscard]] int x() override;
	[[nodiscard]] int y() override;
	~SimplePoint() override = default;
	friend std::ostream &operator<<(std::ostream &os, const SimplePoint &point);
};

#endif
