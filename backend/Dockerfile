# ---------- Build Stage ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Render will assign PORT dynamically
ENV PORT=8080
EXPOSE 8080

# Spring Boot uses environment variables IF YOU SET THEM
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "app.jar"]
