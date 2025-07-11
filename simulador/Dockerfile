FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /simulador
COPY . .
RUN mvn clean compile

FROM eclipse-temurin:17-jdk
WORKDIR /simulador
COPY --from=build /simulador/target/classes ./classes
CMD java -cp classes com.example.alertservice.simulador.SimuladorAlerta
