# Spring DB Demo

Учебный проект на Spring Boot с подключением к MySQL

## Описание

Проект демонстрирует работу с базой данных MySQL через Spring Boot, реализует два справочника:
- **Студенты** (`/students`)
- **Музыкальные инструменты** (`/instruments`)

Главная страница (`/`) содержит ссылки на оба справочника и минималистичный интерфейс.

## Технологии
- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- Lombok
- MySQL (Docker)
- Maven

## Быстрый старт

1. **Запустите MySQL в Docker** (пример):
   ```sh
   docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
   ```
2. **Настройте переменные окружения** (пароль к БД):
   - В Windows используйте запуск через `run-spring.bat`:
     ```sh
     set DB_PASSWORD=yourpassword
     .\run-spring.bat
     ```
3. **Откройте в браузере**:
   - [http://localhost:8080/](http://localhost:8080/) — главная страница
   - [http://localhost:8080/students](http://localhost:8080/students) — студенты
   - [http://localhost:8080/instruments](http://localhost:8080/instruments) — музыкальные инструменты

## Структура проекта
- `src/main/java/com/lukyanov/spring_db/entity/` — сущности (Student, MusicalInstrument)
- `src/main/java/com/lukyanov/spring_db/repository/` — репозитории
- `src/main/java/com/lukyanov/spring_db/service/` — сервисы
- `src/main/java/com/lukyanov/spring_db/controller/` — REST-контроллеры
- `src/main/resources/static/` — интерфейс (index.html, students.html, instruments.html)
- `src/main/resources/application.properties` — настройки подключения

## Особенности
- Корректная поддержка кириллицы (utf8mb4)
- Чистый репозиторий: папка `target/` и временные файлы игнорируются
- Пример тестовых данных для студентов и инструментов

## Запуск тестов
```sh
./mvnw test
```

## Автор
- Gerasha0
