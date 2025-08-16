@echo off
setlocal
if "%DB_PASSWORD%"=="" (
    set /p DB_PASSWORD=Enter DB password: 
)
set DB_PASSWORD=%DB_PASSWORD%
cd spring-db
call mvnw spring-boot:run
endlocal
