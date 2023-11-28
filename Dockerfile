FROM maven:3-openjdk-17 AS build-image

WORKDIR /to-build-app
RUN ./mvnw clean

COPY pom.xml .
COPY ./ ./

RUN ./mvnw dependency:go-offline
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build-image /to-build-app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
