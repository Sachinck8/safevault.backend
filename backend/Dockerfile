#======================
# Build Stage
# ======================
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the backend folder
COPY backend ./backend

# Build the app
WORKDIR /app/backend
RUN mvn clean package -DskipTests

# ======================
# Run Stage
# ======================
FROM eclipse-temurin:17-jdk  
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/backend/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
