FROM openjdk:8
ADD target/docker-order-service.jar docker-order-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-order-service.jar"]