@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo ╔══════════════════════════════════════════════════════════════╗
echo ║         ЛАБОРАТОРНА РОБОТА 2.1 - ВАРІАНТ 7                  ║
echo ║        ДОСЛІДЖЕННЯ МАТЕМАТИЧНИХ АЛГОРИТМІВ                   ║
echo ╚══════════════════════════════════════════════════════════════╝
echo.

echo Компіляція та запуск програми...
echo.

call mvn compile exec:java "-Dexec.mainClass=org.atsd.App" "-Dexec.jvmArgs=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.language=uk -Duser.country=UA"

echo.
echo ═══════════════════════════════════════════════════════════════
echo                    ПРОГРАМА ЗАВЕРШЕНА
echo ═══════════════════════════════════════════════════════════════
echo Натисніть будь-яку клавішу для виходу...
pause >nul
