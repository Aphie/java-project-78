# Makefile
run-dist: # запуск исполняемого файла
	make -C app run-dist

project-build: #сборка проекта
	make -C app project-build

lint-main: #проверка на стандарт кодирования Main
	make -C app lint-main

lint-test: #проверка на стандарт кодирования Test
	make -C app lint-test

run-test: #запуск тестов
	make -C app run-test

.PHONY: build