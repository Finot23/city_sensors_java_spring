# Etapa 1: Compilar el simulador
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /simulador
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar el simulador
FROM eclipse-temurin:17-jdk
WORKDIR /simulador
COPY --from=build /simulador/target/simulador-0.0.1-SNAPSHOT.jar simulador.jar
CMD ["java", "-jar", "simulador.jar"]
