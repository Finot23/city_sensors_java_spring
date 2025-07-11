package com.example.alertservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.alertservice.model.EvaluacionRequest;

@RestController
public class AlertController {

    private final List<EvaluacionRequest> datosRecibidos = new ArrayList<>();
    private final List<String> alertas = new ArrayList<>();

    @PostMapping("/evaluar")
    public Map<String, String> evaluar(@RequestBody EvaluacionRequest data) {
        // Guardar todos los datos recibidos
        datosRecibidos.add(data);

        String barrio = data.getBarrio();
        double temp = data.getTemperatura();
        double calidad = data.getCalidad_aire();

        if (temp > 35 || calidad > 60) {
            String alerta = "ALERTA en " + barrio + ": Temp=" + temp + " °C, Calidad aire=" + calidad;
            alertas.add(alerta);
            System.out.println(alerta);
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "evaluado");
        return response;
    }

    @GetMapping("/alertas")
    public List<String> verAlertas() {
        return alertas;
    }

    @GetMapping("/datos")
    public List<EvaluacionRequest> verDatosRecibidos() {
        return datosRecibidos;
    }

    // Opcional: para evitar error si entras por navegador
    @GetMapping("/evaluar")
    public String infoEvaluar() {
        return "Este endpoint acepta solo POST para evaluar alertas.";
    }
}
