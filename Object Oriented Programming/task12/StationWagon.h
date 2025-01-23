#ifndef STATIONWAGON_H
#define STATIONWAGON_H
#include "PassengerCar.h"
#include "Van.h"

namespace lsv12 {
	class StationWagon : virtual public Van, virtual public PassengerCar {
	private:
		int numSeats;

	public:
		StationWagon(const std::string &numberPlate, const std::string &manufacturer, int maxLoad, bool sunRoof, int numSeats);
		void dump() override;
		[[nodiscard]] int getNumSeats() const;
		//~StationWagon() override = default;
	};
}

#endif
