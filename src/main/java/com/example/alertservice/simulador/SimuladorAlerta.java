/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.alertservice.simulador;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import com.example.alertservice.model.EvaluacionRequest;
        
public class SimuladorAlerta {

    public static void main(String[] args) {
        try {
            Random random = new Random();

            String barrio = "Zona " + (char) ('A' + random.nextInt(5)); // Zona A - E
            double temperatura = 20 + random.nextDouble() * 20;         // 20°C a 40°C
            double calidadAire = 30 + random.nextDouble() * 50;         // 30 a 80

            EvaluacionRequest data = new EvaluacionRequest();
            data.setBarrio(barrio);
            data.setTemperatura(temperatura);
            data.setCalidad_aire(calidadAire);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);

            URL url = new URL("http://localhost:8080/evaluar");
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
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
