cmake_minimum_required(VERSION 3.29)
project(FH_S5_OOP)

set(CMAKE_CXX_STANDARD 20)

add_executable(FH_S5_OOP main.cpp
		task1/task1.cpp task1/task1.h
		task2/task2.cpp task2/task2.h task2/Position.h
		task3/task3.cpp task3/task3.h task3/Position.cpp task3/Position.h
		task4/task4.cpp task4/task4.h task4/City.cpp task4/City.h
		task5/task5.cpp task5/task5.h task5/City.cpp task5/City.h
		task6/task6.cpp task6/task6.h task6/City.cpp task6/City.h
		task7/task7.cpp task7/task7.h task7/Position.cpp task7/Position.h task7/City.cpp task7/City.h
		task8/task8.cpp task8/task8.h task8/City.cpp task8/City.h
		task9/task9.cpp task9/task9.h task9/City.cpp task9/City.h
		task10/task10.cpp task10/task10.h task10/City.cpp task10/City.h
		task11/task11.cpp task11/task11.h task11/Character.h
		task12/task12.cpp task12/task12.h task12/Vehicle.cpp task12/Vehicle.h
		task12/Van.cpp
		task12/Van.h
		task12/PassengerCar.cpp
		task12/PassengerCar.h
		task12/StationWagon.cpp
		task12/StationWagon.h
)
