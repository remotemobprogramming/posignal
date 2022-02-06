FROM eclipse-temurin:17-focal
CMD ["java", "-jar", "/service.jar"]
COPY target/service.jar /service.jar
