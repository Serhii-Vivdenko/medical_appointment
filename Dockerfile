
FROM openjdk:17-jdk-slim

# Установка директории приложения внутри контейнера
WORKDIR /app
# Копирование JAR-файла с вашим приложением в контейнер
COPY target/appointment-0.0.1-SNAPSHOT.jar /app/app.jar

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "app.jar"]
