@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === ЗУПИНКА СИСТЕМИ ДОСТАВКИ ЇЖІ ===
echo === Spring Boot Server Killer ===
echo.

echo Пошук запущених Java процесів...
echo.

:: Пошук процесів Java з Maven Spring Boot
for /f "tokens=2 delims=," %%i in ('tasklist /fi "imagename eq java.exe" /fo csv /nh 2^>nul') do (
    if not "%%~i"=="" (
        echo Знайдено Java процес: %%~i
        set FOUND_JAVA=1
    )
)

:: Пошук процесів Maven
for /f "tokens=2 delims=," %%i in ('tasklist /fi "imagename eq mvn.cmd" /fo csv /nh 2^>nul') do (
    if not "%%~i"=="" (
        echo Знайдено Maven процес: %%~i
        set FOUND_MAVEN=1
    )
)

echo.

if defined FOUND_JAVA (
    echo Зупинка Java процесів...
    taskkill /f /im java.exe >nul 2>&1
    if %errorlevel%==0 (
        echo ✓ Java процеси зупинено успішно
    ) else (
        echo ✗ Помилка при зупинці Java процесів
    )
) else (
    echo ℹ Java процеси не знайдено
)

if defined FOUND_MAVEN (
    echo Зупинка Maven процесів...
    taskkill /f /im mvn.cmd >nul 2>&1
    if %errorlevel%==0 (
        echo ✓ Maven процеси зупинено успішно
    ) else (
        echo ✗ Помилка при зупинці Maven процесів
    )
) else (
    echo ℹ Maven процеси не знайдено
)

:: Додаткова перевірка портів 8080
echo.
echo Перевірка порту 8080...
netstat -ano | findstr :8080 >nul
if %errorlevel%==0 (
    echo ⚠ Порт 8080 все ще зайнятий
    echo Спроба звільнити порт 8080...
    for /f "tokens=5" %%p in ('netstat -ano ^| findstr :8080') do (
        taskkill /f /pid %%p >nul 2>&1
        if %errorlevel%==0 (
            echo ✓ Процес на порту 8080 зупинено
        )
    )
) else (
    echo ✓ Порт 8080 вільний
)

echo.
echo === ЗУПИНКА ЗАВЕРШЕНА ===
echo Тепер можна безпечно запускати сервер знову
echo.
echo Консоль закриється через 3 секунди...
timeout /t 3 /nobreak >nul
exit
