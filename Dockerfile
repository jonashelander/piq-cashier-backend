FROM maven:3.8.4-openjdk-11-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM FROM openjdk:11-jre-slimm
COPY --from=build /target/piq-cashier-backend-0.0.1-SNAPSHOT.jar piq-cashier-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","piq-cashier-backend.jar"]
