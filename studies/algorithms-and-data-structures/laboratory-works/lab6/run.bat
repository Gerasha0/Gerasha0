@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === ЛАБОРАТОРНА РОБОТА 1.6 - ДОСЛІДЖЕННЯ МЕТОДІВ АНАЛІЗУ АЛГОРИТМІВ ===
echo Варіант 7
echo.

echo Компіляція та запуск програми...
echo.

call mvn compile exec:java "-Dexec.mainClass=org.atsd.App" "-Dexec.jvmArgs=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.language=uk -Duser.country=UA"

echo.
echo === ПРОГРАМА ЗАВЕРШЕНА ===
echo Натисніть будь-яку клавішу для виходу...
pause >nul
