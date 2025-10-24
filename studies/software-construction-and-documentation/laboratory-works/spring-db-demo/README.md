# spring-db-demo

<div align="center">

[🇺🇦 Українська](#ukrainian) | [🇬🇧 English](#english)

</div>

---

## <a name="ukrainian"></a>🇺🇦 Українська

Spring Boot навчальний проєкт з інтеграцією MySQL (через Docker), підтримкою кирилиці, безпечним зберіганням пароля через змінні оточення та мінімалістичним веб-інтерфейсом.

### Можливості
- Багаторівнева архітектура: Controller, Service, Repository, Entity
- Використовуються Lombok і Spring Data JPA
- MySQL запускається в Docker (не входить до репозиторію)
- Пароль до БД зберігається тільки в змінній оточення `DB_PASSWORD`
- Два довідники: `/students` (студенти) і `/instruments` (музичні інструменти)
- Веб-інтерфейс для перегляду даних:
	- `/` — головна сторінка з посиланнями
	- `/students` — сторінка зі списком студентів
	- `/instruments` — сторінка зі списком музичних інструментів

### Швидкий старт
1. Запустіть MySQL в Docker (приклад нижче).
2. Встановіть змінну оточення `DB_PASSWORD`.
3. Запустіть проєкт через `run-spring.bat` або `./mvnw spring-boot:run`.

#### Приклад запуску MySQL в Docker
```bash
docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
```

### Веб-інтерфейс
- [http://localhost:8080/](http://localhost:8080/) — головна сторінка
- [http://localhost:8080/students](http://localhost:8080/students) — студенти
- [http://localhost:8080/instruments](http://localhost:8080/instruments) — музичні інструменти

### Примітки
- База даних та тестові дані не входять до репозиторію
- Всі артефакти збірки виключені через `.gitignore`

---

## <a name="english"></a>🇬🇧 English

Spring Boot educational project with MySQL integration (via Docker), Cyrillic support, secure password storage via environment variables, and a minimalist web interface.

### Features
- Multi-layered architecture: Controller, Service, Repository, Entity
- Uses Lombok and Spring Data JPA
- MySQL runs in Docker (not included in repository)
- Database password stored only in `DB_PASSWORD` environment variable
- Two reference tables: `/students` and `/instruments` (musical instruments)
- Web interface for data viewing:
	- `/` — home page with links
	- `/students` — students list page
	- `/instruments` — musical instruments list page

### Quick Start
1. Run MySQL in Docker (example below).
2. Set the `DB_PASSWORD` environment variable.
3. Run the project via `run-spring.bat` or `./mvnw spring-boot:run`.

#### MySQL Docker Launch Example
```bash
docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=herman_db -p 3306:3306 -d mysql:8.0
```

### Web Interface
- [http://localhost:8080/](http://localhost:8080/) — home page
- [http://localhost:8080/students](http://localhost:8080/students) — students
- [http://localhost:8080/instruments](http://localhost:8080/instruments) — musical instruments

### Notes
- Database and test data are not included in the repository
- All build artifacts are excluded via `.gitignore`
