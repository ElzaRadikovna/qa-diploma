# Дипломный проект по профессии «Тестировщик»

Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Описание приложения

Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

1. Обычная оплата по дебетовой карте.
1. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- сервису платежей, далее Payment Gate;
- кредитному сервису, далее Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## Prerequisites

**Необходимые ПО для установки на ПК:**

- GIT
- Браузер
- IntelliJ IDEA
- Docker <a name="_hlk180492302"></a>Desktop


## Начало работы

1. Клонировать репозиторий на локальный ПК:

> git clone <https://github.com/ElzaRadikovna/qa-diploma.git>




## Установка и запуск 

1. Открыть проект в IntelliJ IDEA
1. Запустить контейнеры в терминале IntelliJ IDEA, набрав команду:

docker-compose up

1. Открыть новую вкладку и запустить приложение командой:
- MySQL: 

  > java -jar./artifacts/aqa-shop.jar P:jdbc.url=jdbc:mysql://185.119.57.64:3306/app

- PostgreSQL:

  > java -jar./artifacts/aqa-shop.jar P:jdbc.url=jdbc: postgresql://185.119.57.64:5432/app

1. Проверить доступ к приложению в браузере:

[             http://localhost:8080/](%20%20%20%20%20%20%20%20%20%20%20%20%20http:/localhost:8080/)

## Запуск тестов

В новой вкладке терминала набрать команду:

- MySQL:

> ./gradlew clean test -D:db.url=jdbc:mysql://185.119.57.172:3306/app

- PostgreSQL:

> ./gradlew clean test -D:db.url=jdbc:postgresql://185.119.57.172:5432/app





## Лицензия

Свободная
