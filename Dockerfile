FROM eclipse-temurin:17-jdk
COPY build/libs/email-service.jar .
ENTRYPOINT ["java", "-jar", "email-service.jar"]