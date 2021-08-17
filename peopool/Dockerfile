FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul

RUN apk add --no-cache tzdata
