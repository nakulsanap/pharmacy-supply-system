FROM openjdk:8-jdk-alpine
EXPOSE 8083
COPY ./target/pharmacysupply.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]