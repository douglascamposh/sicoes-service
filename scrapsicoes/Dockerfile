FROM --platform=linux/amd64 eclipse-temurin:17.0.8_7-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
