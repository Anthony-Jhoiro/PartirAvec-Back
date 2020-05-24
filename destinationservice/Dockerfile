# FROM openjdk:11
# EXPOSE 8080
# ARG JAR_FILE=target/*.jar
# ADD ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD


COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build/

RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/*.jar /app/

ENTRYPOINT ["java", "-jar", "/app/*.jar"]