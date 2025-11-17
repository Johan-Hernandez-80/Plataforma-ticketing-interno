FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
COPY .env /app/.env 

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
