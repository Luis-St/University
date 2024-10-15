cmake_minimum_required(VERSION 3.29)
project(FH_S5_OOP)

set(CMAKE_CXX_STANDARD 20)

add_executable(FH_S5_OOP main.cpp task1/task1.cpp task1/task1.h
		task2/task2.cpp
		task2/task2.h
		task2/Position.h)
