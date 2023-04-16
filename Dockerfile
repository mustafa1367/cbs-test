FROM gradle:7.2.0-jdk11 AS build
WORKDIR /usr/src/app
COPY . .
RUN gradle clean build -x test

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /usr/src/app/build/libs/*SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "app.jar"]