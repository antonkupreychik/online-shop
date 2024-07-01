# Используем базовый образ для сборки приложения
FROM openjdk:17-slim AS build
WORKDIR /workspace/app

# Копирование файлов gradle и исходного кода в контейнер
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src

# Сборка приложения
RUN ./gradlew build -x test

# Используем другой базовый образ для запуска собранного приложения
FROM openjdk:17-slim
WORKDIR /app

# Определите аргументы для переменных окружения
ARG BOOTSTRAP_SERVERS
ARG DAILY_STATS

# Установите переменные окружения внутри контейнера
ENV BOOTSTRAP_SERVERS=${BOOTSTRAP_SERVERS}
ENV DAILY_STATS=${DAILY_STATS}

# Копирование собранного jar файла из этапа build
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Запуск приложения
CMD ["java", "-jar", "app.jar"]
