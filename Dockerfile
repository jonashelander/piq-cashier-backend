# Use the official Maven image as a base image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Create a new image with the JRE only
FROM eclipse-temurin:11

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/PIQResponseMock.jar .

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "PIQResponseMock.jar"]
