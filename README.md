#Mini-Proyecto "city_monitor_springboot"
(Sistema Distribuido para Monitoreo Ambiental)

UEA: Sistemas Distribuidos

👥 Autores:

    - Andrés Mateo Dorantes
    - José Abraham Marín Sánchez
    - Luis Antonio Salinas Mata

Objetivo:

Desarrollar un sistema distribuido que monitoree variables ambientales (temperatura y calidad del aire) en distintos barrios de una ciudad, utilizando una arquitectura basada en microservicios desacoplados.

La solución emplea:

    - Java 17
    - Spring Boot
    - Comunicación RESTful
    - Contenedores Docker para despliegue

#Arquitectura General

El sistema está compuesto por los siguientes módulos:

    - SimuladorAlerta: Cliente Java que simula sensores de barrios, generando datos ambientales aleatorios periódicamente y enviándolos vía HTTP POST.

    - AlertService: Servicio Spring Boot que:

        Recibe datos del simulador mediante POST.

        Evalúa si las lecturas superan umbrales críticos.

        Registra los datos recibidos y genera alertas si aplica.

        Expone endpoints GET para consultar datos y alertas.

📌 Diagrama de arquitectura:



⚙️ Tecnologías Usadas

    Lenguaje: Java 17

    Framework: Spring Boot 3.x

    Comunicación: HTTP REST

    Serialización: JSON (Jackson)

    IDE recomendado: NetBeans

🚨 Reglas de Generación de Alertas

El servicio generará una alerta cuando:

    Temperatura > 35 °C

    Calidad del aire > 60

📦 Ejemplo de alerta generada:

ALERTA en Zona C: Temp=38.6 °C, Calidad aire=67.2

📡 API del servicio (AlertService)
| Método | Ruta     | Descripción                                               |
| ------ | -------- | --------------------------------------------------------- |
| POST   | /evaluar | Recibe datos JSON y evalúa si hay alerta                  |
| GET    | /alertas | Devuelve la lista actual de alertas generadas             |
| GET    | /datos   | Devuelve todas las lecturas recibidas                     |
| GET    | /evaluar | (Opcional) Mensaje informativo si se accede vía navegador |

```json
Los datos se reciben en formato JSON como:
{
  "barrio": "Zona B",
  "temperatura": 36.5,
  "calidad_aire": 65.3
}
```
🚀 Cómo ejecutar

    Clona el repositorio:
```bash
    git clone https://github.com/TU-USUARIO/city_monitor_springboot
cd city_monitor_springboot
```
Ejecuta el servicio:
```bash
./mvnw spring-boot:run
```
En paralelo, ejecuta el simulador:

```bash
# Desde tu IDE: ejecuta SimuladorAlerta.java
# O desde terminal:
mvn compile
mvn exec:java -Dexec.mainClass="com.example.alertservice.simulador.SimuladorAlerta"
```
Verifica en navegador:

    http://localhost:8080/alertas → lista de alertas

    http://localhost:8080/datos → datos ambientales recibidos

    
