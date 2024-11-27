FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache maven
WORKDIR /app

COPY pom.xml pom.xml
RUN mvn dependency:go-offline -X

COPY src/ src/
RUN mvn package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/busca-cep-0.0.1-SNAPSHOT.jar"]