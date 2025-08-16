# spring-db-demo

Spring Boot учебный проект с интеграцией MySQL (через Docker), поддержкой кириллицы, безопасным хранением пароля через переменные окружения и минималистичным веб-интерфейсом.

## Возможности
- Многоуровневая архитектура: Controller, Service, Repository, Entity
- Используются Lombok и Spring Data JPA
- MySQL запускается в Docker (не входит в репозиторий)
- Пароль к БД хранится только в переменной окружения `DB_PASSWORD`
- Два справочника: `/students` (студенты) и `/instruments` (музыкальные инструменты)
- Веб-интерфейс для просмотра данных:
	- `/` — главная страница со ссылками
	- `/students` — страница со списком студентов
	- `/instruments` — страница со списком музыкальных инструментов

## Быстрый старт
1. Запустите MySQL в Docker (пример ниже).
2. Установите переменную окружения `DB_PASSWORD`.
3. Запустите проект через `run-spring.bat` или `./mvnw spring-boot:run`.

### Пример запуска MySQL в Docker
```
docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
```

## Веб-интерфейс
- [http://localhost:8080/](http://localhost:8080/) — главная страница
- [http://localhost:8080/students](http://localhost:8080/students) — студенты
- [http://localhost:8080/instruments](http://localhost:8080/instruments) — музыкальные инструменты

## Примечания
- База данных и тестовые данные не входят в репозиторий
- Все артефакты сборки исключены через `.gitignore`
