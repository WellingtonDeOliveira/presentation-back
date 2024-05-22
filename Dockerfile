FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/spring-boot-presentation-project-1.0.0.jar spring-boot-presentation-project-1.0.0.jar
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-presentation-project-1.0.0.jar"]