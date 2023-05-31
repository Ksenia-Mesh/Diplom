# Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Документы:
* [План автоматизации](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Plan.md/) 
* [Отчётные документы по итогам тестирования](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Report.md/) 
* [Отчётные документы по итогам автоматизации](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Summary.md/) 




Запустить Docker Desktop

убедитесь, что порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны

Склонировать репозиторий на локальный компьютер командой в Git:

Запустить IntelliJ IDEA и открыть проект.


Инструкции по установке
Скачайте архив;

Запустите контейнер, в котором разворачивается база данных (далее БД) docker-compose up -d --force-recreate

Убедитесь в том, что БД готова к работе (логи смотреть через docker-compose logs -f <applicationName> (mysql или postgres)

Запустить SUT во вкладке Terminal Intellij IDEA командой: java -jar artifacts/aqa-shop.jar

Для запуска авто-тестов в Terminal Intellij IDEA открыть новую сессию и ввести команду: ./gradlew clean test allureReport -Dheadless=true где: allureReport - подготовка данных для отчета Allure; -Dheadless=true - запускает авто-тесты в headless-режиме (без открытия браузера).

Для просмотра отчета Allure в терминале ввести команду: ./gradlew allureServe
