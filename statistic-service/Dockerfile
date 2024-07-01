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
ARG DATABASE_URL
ARG DATABASE_USER
ARG DATABASE_PASSWORD
ARG DEFAULT_SCHEMA
ARG LIQUIBASE_SCHEMA
ARG KEYCLOAK_JWK_URL
ARG BOOTSTRAP_SERVERS
ARG PORT

# Установите переменные окружения внутри контейнера
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_USER=${DATABASE_USER}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV DEFAULT_SCHEMA=${DEFAULT_SCHEMA}
ENV LIQUIBASE_SCHEMA=${LIQUIBASE_SCHEMA}
ENV KEYCLOAK_JWK_URL=${KEYCLOAK_JWK_URL}
ENV BOOTSTRAP_SERVERS=${BOOTSTRAP_SERVERS}
ENV PORT=${PORT}

# Копирование собранного jar файла из этапа build
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Запуск приложения
CMD ["java", "-jar", "app.jar"]
