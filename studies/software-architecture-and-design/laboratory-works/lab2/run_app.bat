@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === ЗАПУСК СИСТЕМЫ ДОСТАВКИ СТРАВ ===
echo.

mvn compile exec:java "-Dexec.mainClass=com.example2.App" "-Dexec.jvmArgs=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Duser.language=uk -Duser.country=UA" -q

echo.
echo === ПРИЛОЖЕНИЕ ЗАВЕРШЕНО ===
pause
