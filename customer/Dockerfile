FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY target/customer-0.0.1.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]