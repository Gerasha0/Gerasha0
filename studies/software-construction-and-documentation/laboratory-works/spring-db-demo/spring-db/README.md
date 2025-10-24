# Spring DB Demo

<div align="center">

[🇺🇦 Українська](#ukrainian) | [🇬🇧 English](#english)

</div>

---

## <a name="ukrainian"></a>🇺🇦 Українська

Навчальний проєкт на Spring Boot з підключенням до MySQL

### Опис

Проєкт демонструє роботу з базою даних MySQL через Spring Boot, реалізує два довідники:
- **Студенти** (`/students`)
- **Музичні інструменти** (`/instruments`)

Головна сторінка (`/`) містить посилання на обидва довідники та мінімалістичний інтерфейс.

### Технології
- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- Lombok
- MySQL (Docker)
- Maven

### Швидкий старт

1. **Запустіть MySQL в Docker** (приклад):
   ```bash
   docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
   ```
2. **Налаштуйте змінні оточення** (пароль до БД):
   - В Windows використовуйте запуск через `run-spring.bat`:
     ```bash
     set DB_PASSWORD=yourpassword
     .\run-spring.bat
     ```
3. **Відкрийте в браузері**:
   - [http://localhost:8080/](http://localhost:8080/) — головна сторінка
   - [http://localhost:8080/students](http://localhost:8080/students) — студенти
   - [http://localhost:8080/instruments](http://localhost:8080/instruments) — музичні інструменти

### Структура проєкту
- `src/main/java/com/lukyanov/spring_db/entity/` — сутності (Student, MusicalInstrument)
- `src/main/java/com/lukyanov/spring_db/repository/` — репозиторії
- `src/main/java/com/lukyanov/spring_db/service/` — сервіси
- `src/main/java/com/lukyanov/spring_db/controller/` — REST-контролери
- `src/main/resources/static/` — інтерфейс (index.html, students.html, instruments.html)
- `src/main/resources/application.properties` — налаштування підключення

### Особливості
- Коректна підтримка кирилиці (utf8mb4)
- Чистий репозиторій: папка `target/` та тимчасові файли ігноруються
- Приклад тестових даних для студентів та інструментів

### Запуск тестів
```bash
./mvnw test
```

### Автор
- Gerasha0

---

## <a name="english"></a>🇬🇧 English

Educational Spring Boot project with MySQL connection

### Description

The project demonstrates working with MySQL database through Spring Boot, implements two reference tables:
- **Students** (`/students`)
- **Musical Instruments** (`/instruments`)

The home page (`/`) contains links to both reference tables and a minimalist interface.

### Technologies
- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- Lombok
- MySQL (Docker)
- Maven

### Quick Start

1. **Run MySQL in Docker** (example):
   ```bash
   docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
   ```
2. **Configure environment variables** (database password):
   - On Windows, use launch via `run-spring.bat`:
     ```bash
     set DB_PASSWORD=yourpassword
     .\run-spring.bat
     ```
3. **Open in browser**:
   - [http://localhost:8080/](http://localhost:8080/) — home page
   - [http://localhost:8080/students](http://localhost:8080/students) — students
   - [http://localhost:8080/instruments](http://localhost:8080/instruments) — musical instruments

### Project Structure
- `src/main/java/com/lukyanov/spring_db/entity/` — entities (Student, MusicalInstrument)
- `src/main/java/com/lukyanov/spring_db/repository/` — repositories
- `src/main/java/com/lukyanov/spring_db/service/` — services
- `src/main/java/com/lukyanov/spring_db/controller/` — REST controllers
- `src/main/resources/static/` — interface (index.html, students.html, instruments.html)
- `src/main/resources/application.properties` — connection settings

### Features
- Correct Cyrillic support (utf8mb4)
- Clean repository: `target/` folder and temporary files are ignored
- Example test data for students and instruments

### Running Tests
```bash
./mvnw test
```

### Author
- Gerasha0
