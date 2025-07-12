package com.example.alertservice.simulador;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.example.alertservice.model.EvaluacionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimuladorAlerta {

    public static void main(String[] args) {
        try {
            Random random = new Random();
            ObjectMapper mapper = new ObjectMapper();

            while (true) { // bucle infinito

                String barrio = "Zona " + (char) ('A' + random.nextInt(5)); // Zona A - E
                double temperatura = 20 + random.nextDouble() * 20;         // 20°C a 40°C
                double calidadAire = 30 + random.nextDouble() * 50;         // 30 a 80

                EvaluacionRequest data = new EvaluacionRequest();
                data.setBarrio(barrio);
                data.setTemperatura(temperatura);
                data.setCalidad_aire(calidadAire);

                String json = mapper.writeValueAsString(data);

                URL url = new URL("http://alertservice:8080/evaluar");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes());
                    os.flush();
                }

                int responseCode = conn.getResponseCode();
                System.out.println("Respuesta: " + responseCode + " (" + conn.getResponseMessage() + ")");
                System.out.println("Datos enviados: " + json);
                conn.disconnect();

                Thread.sleep(5000); // espera 5 segundos antes de enviar el siguiente
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
