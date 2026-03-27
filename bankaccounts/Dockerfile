FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY target/bankaccounts-0.0.1.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]