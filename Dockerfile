# Etapa 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Empaquetar la aplicación sin correr pruebas
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera solo con el JAR final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/target/citySensors-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
