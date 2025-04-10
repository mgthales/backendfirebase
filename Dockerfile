# Etapa 1 - build da aplicação com Maven
FROM maven:3.9.6-openjdk-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2 - imagem leve só com o jar
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/backendfirebase-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
