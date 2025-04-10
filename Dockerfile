# Usa a imagem oficial do OpenJDK 17
FROM openjdk:21

# Copia o JAR compilado para dentro do container
COPY target/backendfirebase-0.0.1-SNAPSHOT.jar app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "/app.jar"]

