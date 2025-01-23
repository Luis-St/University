#ifndef PASSENGERCAR_H
#define PASSENGERCAR_H
#include "Vehicle.h"

namespace lsv12 {
	class PassengerCar : virtual public Vehicle {
	private:
		bool sunRoof;

	public:
		PassengerCar(const std::string &numberPlate, const std::string &manufacturer, bool sunRoof);
		void dump() override;
		[[nodiscard]] bool hasSunRoof() const;
		//~PassengerCar() override = default;
	};
}

#endif
