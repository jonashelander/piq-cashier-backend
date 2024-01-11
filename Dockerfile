FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jre-slim
COPY --from=build /target/piq-cashier-backend-0.0.1-SNAPSHOT.jar piq-cashier-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","piq-cashier-backend.jar"]
