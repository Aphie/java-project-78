# Makefile
run-dist: # запуск исполняемого файла
	./build/install/app/bin/app

project-build: #сборка проекта
	./gradlew clean build

lint-main: #проверка на стандарт кодирования Main
	./gradlew checkstyleMain

lint-test: #проверка на стандарт кодирования Test
	./gradlew checkstyleTest

run-test: #запуск тестов
	./gradlew test

.PHONY: build