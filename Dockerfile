FROM maven:3-eclipse-temurin-17-alpine AS builder
WORKDIR app

COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S parquimetro && adduser -S parquimetro -G parquimetro
USER parquimetro
COPY --from=builder /app/target/*.jar /app/parquimetro-api.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Duser.timezone=America/Fortaleza", "/app/parquimetro-api.jar"]