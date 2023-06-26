FROM openjdk:8-jdk-alpine
EXPOSE 8082
COPY ./target/medicalrepscheduleservice.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]