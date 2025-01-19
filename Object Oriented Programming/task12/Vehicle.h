#ifndef VEHICLE_H
#define VEHICLE_H
#include <string>

namespace lsv12 {
	class Vehicle {
	private:
		const std::string numberPlate;
		const std::string manufacturer;

	public:
		Vehicle(const std::string &numberPlate, const std::string &manufacturer);
		virtual void dump();
		[[nodiscard]] const std::string &getNumberPlate() const;
		[[nodiscard]] const std::string &getManufacturer() const;
		//virtual ~Vehicle() = default;
	};
}

#endif
