# Makefile
run-dist: # запуск исполняемого файла
	./build/install/app/bin

project-build: #сборка проекта
	gradle clean build

lint-main: #проверка на стандарт кодирования Main
	gradle checkstyleMain

lint-test: #проверка на стандарт кодирования Test
	gradle checkstyleTest

run-test: #запуск тестов

gradle test
.PHONY: build