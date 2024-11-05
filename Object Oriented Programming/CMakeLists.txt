cmake_minimum_required(VERSION 3.29)
project(FH_S5_OOP)

set(CMAKE_CXX_STANDARD 20)

add_executable(FH_S5_OOP main.cpp task1/task1.cpp task1/task1.h
		task2/task2.cpp task2/task2.h task2/Position.h
		task3/task3.cpp task3/task3.h task3/Position.cpp task3/Position.h
		task4/task4.cpp task4/task4.h task4/City.cpp task4/City.h
		task5/task5.cpp task5/task5.h task5/City.cpp
)
