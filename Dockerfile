FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11
COPY --from=build /piq_response_mock-0.0.1-SNAPSHOT.jar piq_response_mock.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","piq-cashier-backend.jar"]
