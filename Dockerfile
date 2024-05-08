FROM openjdk:17-alpine AS build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:17-alpine
ARG JAR_FILE=/workspace/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar

EXPOSE 8080

ENV DB_HOST=localhost:5432
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=@753776789!
ENV JWT_SECRET_KEY=0986430@753776789!1208@948741157
ENV JWT_EXPIRATION=86400000
ENV JWT_ISSUER=base-project-api

ENTRYPOINT ["java", "-jar", "/app.jar"]