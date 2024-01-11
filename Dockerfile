FROM maven:3.8.4-openjdk-11-slim AS build
COPY . .
RUN mvn clean package

FROM openjdk:11-jre-slim
COPY --from=build /target/piq-cashier-backend-0.0.1-SNAPSHOT.jar piq-cashier-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","piq-cashier-backend.jar"]

process "/bin/sh -c mvn clean package
