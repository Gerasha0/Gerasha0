@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === ЗАПУСК СИСТЕМИ ДОСТАВКИ ЇЖІ ===
echo === Spring Boot Server ===
echo.
echo Компіляція проекту...
call mvn compile
echo.
echo ✅ Компіляція завершена
echo ▶️ Запуск Spring Boot сервера...
echo Сервер буде доступний за адресою: http://localhost:8080/
echo Тестовий API інтерфейс: http://localhost:8080/
echo Сайт ресторану: http://localhost:8080/restaurant.html
echo.
echo Для зупинки сервера натисніть Ctrl+C
echo.

call mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.language=uk -Duser.country=UA"

echo.
echo === СЕРВЕР ЗУПИНЕНО ===
pause
