
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=TARGET/*.JAR
COPY ./hotel-backend/target/hotel-0.0.1.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]