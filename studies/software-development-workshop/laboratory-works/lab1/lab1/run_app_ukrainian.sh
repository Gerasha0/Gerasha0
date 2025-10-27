#!/bin/bash
# Script to run Ukrainian version of the application

echo "Компіляція проекту..."
mvn compile

if [ $? -eq 0 ]; then
    echo ""
    echo "Запуск української версії..."
    java -Dfile.encoding=UTF-8 \
         -Dconsole.encoding=UTF-8 \
         -Dsun.jnu.encoding=UTF-8 \
         -Duser.language=uk \
         -Duser.country=UA \
         -cp target/classes com.mrpz1.AppUkrainian
else
    echo "Помилка компіляції!"
    exit 1
fi
