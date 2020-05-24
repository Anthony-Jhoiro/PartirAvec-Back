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

FROM openjdk:11

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/uploadservice-0.0.1-SNAPSHOT.jar /app/
RUN ls /app

ENTRYPOINT ["java", "-jar", "/app/uploadservice-0.0.1-SNAPSHOT.jar"]