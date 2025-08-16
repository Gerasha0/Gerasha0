@echo off
chcp 65001 > nul
echo ======================================
echo   ЛАБОРАТОРНА РОБОТА 2.3
echo   Дослідження комбінаторних алгоритмів
echo   Варіант 7
echo ======================================
echo.

echo Компіляція проекту...
mvn clean compile -q

if %errorlevel% neq 0 (
    echo Помилка компіляції!
    pause
    exit /b 1
)

echo Запуск програми...
echo.
java -cp target/classes org.atsd.App

echo.
echo ======================================
echo Програма завершила роботу.
echo ======================================
pause
