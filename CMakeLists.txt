cmake_minimum_required(VERSION 3.15)
project(BuildyBot)

set(CMAKE_CXX_STANDARD 14)

add_executable(BuildyBot main.cpp Game.cpp Game.h)