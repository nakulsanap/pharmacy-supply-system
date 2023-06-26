FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY ./target/medicinestockservice.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]