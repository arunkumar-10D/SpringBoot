FROM openjdk:17-jdk
ADD target/spring-app.jar spring-app.jar
ENTRYPOINT ["java", "-jar", "/spring-app.jar"]