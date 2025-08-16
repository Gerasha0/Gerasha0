@echo off
chcp 65001 >nul
cd /d "%~dp0"

echo === TEXT PROCESSING SYSTEM (ENGLISH) ===
echo.

call mvn compile exec:java "-Dexec.mainClass=com.mrpz1.App" "-Dexec.jvmArgs=-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"

echo.
echo === APPLICATION COMPLETED ===
echo Press any key to exit...
pause >nul
