FROM openjdk:21-jdk
MAINTAINER se-timofeev@icloud.com
COPY target/stableWork-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
