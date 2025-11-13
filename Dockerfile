# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

# Use the Railway-provided PORT env variable
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
