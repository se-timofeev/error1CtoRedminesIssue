FROM openjdk:21-jdk
MAINTAINER se-timofeev@icloud.com
COPY target/app01.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
