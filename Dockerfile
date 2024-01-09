FROM maven:3:8:5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.01.1-jdk-slim
COPY --from=build /target/piq-cashier-backend-0.0.1-SNAPSHOT.jar piq-cashier-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","piq-cashier-backend.jar"]
