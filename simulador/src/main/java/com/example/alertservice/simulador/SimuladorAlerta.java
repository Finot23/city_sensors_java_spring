package com.example.alertservice.simulador;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.example.alertservice.model.EvaluacionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimuladorAlerta {
    public static void main(String[] args) {
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            try {
                String barrio = "Barrio " + (char) ('A' + random.nextInt(5));
                double temperatura = 36 + random.nextDouble() * 5; // asegura alerta
                double calidadAire = 61 + random.nextDouble() * 10;

                EvaluacionRequest data = new EvaluacionRequest();
                data.setBarrio(barrio);
                data.setTemperatura(temperatura);
                data.setCalidad_aire(calidadAire);

                String json = mapper.writeValueAsString(data);

                URL url = new URL("http://alertservice:8080/evaluar");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                try (OutputStream os = con.getOutputStream()) {
                    os.write(json.getBytes());
                    os.flush();
                }

                System.out.printf("[✓] Enviado → %-10s | Temp=%.2f °C | Aire=%.2f\n", barrio, temperatura, calidadAire);
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
