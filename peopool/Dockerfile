FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","${JAVA_OPTS}"]
ENV JAVA_OPTS="-Dspring-boot.run.jvmArguments='-Dkms.url=ws://52.79.162.52:8888/kurento'"
