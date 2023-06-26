FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY ./target/portalservice.war app.war
ENTRYPOINT ["java","-jar","/app.war"]