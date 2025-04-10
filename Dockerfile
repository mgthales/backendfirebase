# Etapa 1 - build da aplicação com Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2 - imagem final leve só com o JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/backendfirebase-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
