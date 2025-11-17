# --- Etapa de construcción ---
FROM gradle:8.8-jdk21 AS build

WORKDIR /app

# Copiamos TODOS los archivos necesarios para el build
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src

# Construimos el .jar
RUN gradle clean bootJar --no-daemon

# --- Etapa final ---
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/build/libs/*.jar app.jar

# Copiamos el .env (asegúrate de que está junto al Dockerfile)
COPY .env .env

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
