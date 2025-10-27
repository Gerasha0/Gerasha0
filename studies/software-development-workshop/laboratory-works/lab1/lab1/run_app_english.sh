#!/bin/bash
# Script to run English version of the application

echo "Compiling the project..."
mvn compile

if [ $? -eq 0 ]; then
    echo ""
    echo "Running English version..."
    java -Dfile.encoding=UTF-8 -cp target/classes com.mrpz1.App
else
    echo "Compilation failed!"
    exit 1
fi
