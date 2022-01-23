FROM openjdk:11
ADD ./target/springboot-uploadfile-s3-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]