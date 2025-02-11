#ifndef VAN_H
#define VAN_H
#include "Vehicle.h"

namespace lsv12 {
	class Van : virtual public Vehicle {
	private:
		int maxLoad;

	public:
		Van(const std::string &numberPlate, const std::string &manufacturer, int maxLoad);
		void dump() override;
		[[nodiscard]] int getMaxLoad() const;
		~Van() override = default;
	};
}

#endif
