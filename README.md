# Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Документы:
* [План автоматизации](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Plan.md/) 
* [Отчётные документы по итогам тестирования](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Report.md/) 
* [Отчётные документы по итогам автоматизации](https://github.com/Ksenia-Mesh/Diplom/blob/main/Documents/Summary.md/) 


## Инструкции по установке

1. Запустить Docker Desktop (скачать если еобходимо: [Docker Desktop](https://www.docker.com/products/docker-desktop/))

2. Убедитесь, что порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны.

3. Склонировать репозиторий на локальный компьютер командой в Git:
```
git clone https://github.com/Ksenia-Mesh/Diplom.git
```
4. Запустить IntelliJ IDEA, скачать при необходимости ([IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows/)).
5. Открыть проект.

## Инструкция по запуску

1. Запустите контейнер, в котором разворачивается база данных:
```
docker-compose up -d --force-recreate
```
2. Запустить SUT во вкладке Terminal Intellij IDEA командой:
```
java -jar artifacts/aqa-shop.jar
```
## Запуск тестов и формироание отчета
В новой вкладке терминала ввести команду:  
```
./gradlew clean test allureReport -Dheadless=true
```
```
./gradlew allureServe
```

